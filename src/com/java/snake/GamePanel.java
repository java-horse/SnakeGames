package com.java.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author mabin
 * @date 2020/3/13 16:10
 * 游戏面板（主要逻辑）
 */

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //方向
    private static final String RIGHT = "R";
    private static final String LEFT = "L";
    private static final String UP = "U";
    private static final String DOWN = "D";
    //距离
    private static final int TOP = 75;
    private static final int BUTTOM = 650;
    private static final int EAST = 25;
    private static final int WEAT = 850;


    //定义变量
    int length = 0; //长度
    int[] snakeX = new int[600]; //坐标X
    int[] snakeY = new int[600]; //坐标Y

    String fx; //蛇头方向
    boolean isStart = false; //游戏是否开始
    Timer timer = new Timer(100, this); //定时器（一秒10次）
    //定义食物坐标
    int footX;
    int footY;
    Random random = new Random();
    //死亡判断
    boolean isFail = false;
    //积分系统
    int score;


    //构造器调用初始化方法
    public GamePanel() {
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        length = 4;
        snakeX[0] = 100;
        snakeY[0] = 100; //头部坐标
        snakeX[1] = 75;
        snakeY[1] = 100; //第一个身体
        snakeX[2] = 50;
        snakeY[2] = 100; //第二个身体
        snakeX[3] = 25;
        snakeY[3] = 100; //第三个身体
        fx = RIGHT;
        //初始化键盘监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        //初始化定时器
        timer.start();
        //初始化食物坐标
        footX = EAST + EAST * random.nextInt(32);
        footY = TOP + EAST * random.nextInt(22);
        //初始化分数
        score = 0;

    }

    /**
     * 画笔
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        //绘制顶部栏
        SnakeDate.header.paintIcon(this, g, EAST, 11);
        //绘制游戏区域
        g.fillRect(EAST, TOP, WEAT, 600);
        //画一条小蛇(蛇头方向)
        if (RIGHT.equals(fx)) {
            SnakeDate.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (LEFT.equals(fx)) {
            SnakeDate.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (UP.equals(fx)) {
            SnakeDate.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (DOWN.equals(fx)) {
            SnakeDate.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //蛇身子
        for (int i = 1; i < length; i++) {
            SnakeDate.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        //画食物
        SnakeDate.food.paintIcon(this, g, footX, footY);
        //画分数
        g.setColor(Color.RED);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度>> " + length, 750, 32);
        g.drawString("分数>> " + score, 750, 52);
        //画标题
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.drawString("Free Games", 335, 49);

        //游戏提示
        if (!isStart) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按空格开始游戏", 300, 400);
        }
        //失败提示
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("Game Over!!!", 300, 300);
            g.drawString("按空格重新开始游戏", 300, 400);
        }

    }

    /**
     * 监听键盘输入
     * 键盘按下，未释放触发的事件
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {  //游戏失败，按空格重新初始化
                isFail = false;
                init();
            } else {  //没有失败，按空格暂停游戏
                isStart = !isStart;
            }
            repaint();//刷新页面，重新加载所有方法

        }
        //键盘控制走向（不允许直接反向掉头）
        if (keyCode == KeyEvent.VK_LEFT && !(RIGHT.equals(fx))) {
            fx = LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT && !(LEFT.equals(fx))) {
            fx = RIGHT;
        } else if (keyCode == KeyEvent.VK_UP && !(DOWN.equals(fx))) {
            fx = UP;
        } else if (keyCode == KeyEvent.VK_DOWN && !(UP.equals(fx))) {
            fx = DOWN;
        }

    }


    /**
     * 定时器
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态并没有失败
        if (isStart && !isFail) {
            //移动身子
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (RIGHT.equals(fx)) {
                snakeX[0] = snakeX[0] + EAST; //头部移动和边界值
                if (snakeX[0] > WEAT) {
                    snakeX[0] = EAST;
                }
            } else if (LEFT.equals(fx)) {
                snakeX[0] = snakeX[0] - EAST; //头部移动和边界值
                if (snakeX[0] < EAST) {
                    snakeX[0] = WEAT;
                }
            } else if (UP.equals(fx)) {
                snakeY[0] = snakeY[0] - EAST; //头部移动和边界值
                if (snakeY[0] < TOP) {
                    snakeY[0] = BUTTOM;
                }
            } else if (DOWN.equals(fx)) {
                snakeY[0] = snakeY[0] + EAST; //头部移动和边界值
                if (snakeY[0] > BUTTOM) {
                    snakeY[0] = TOP;
                }
            }

            //吃食物并重新生成
            if (snakeX[0] == footX && snakeY[0] == footY) {
                length++;
                snakeX[length - 1] = footX - 1;
                snakeY[length - 1] = footY - 1;
                score += 10;
                footX = EAST + EAST * random.nextInt(32);
                footY = TOP + EAST * random.nextInt(22);
            }
            //是否死亡(身体和头重合)
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }
            repaint();
        }
        timer.start(); //让时间动起来
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下弹起
    }
}
