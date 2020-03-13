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
 * ��Ϸ��壨��Ҫ�߼���
 */

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //����
    private static final String RIGHT = "R";
    private static final String LEFT = "L";
    private static final String UP = "U";
    private static final String DOWN = "D";
    //����
    private static final int TOP = 75;
    private static final int BUTTOM = 650;
    private static final int EAST = 25;
    private static final int WEAT = 850;


    //�������
    int length = 0; //����
    int[] snakeX = new int[600]; //����X
    int[] snakeY = new int[600]; //����Y

    String fx; //��ͷ����
    boolean isStart = false; //��Ϸ�Ƿ�ʼ
    Timer timer = new Timer(100, this); //��ʱ����һ��10�Σ�
    //����ʳ������
    int footX;
    int footY;
    Random random = new Random();
    //�����ж�
    boolean isFail = false;
    //����ϵͳ
    int score;


    //���������ó�ʼ������
    public GamePanel() {
        init();
    }

    /**
     * ��ʼ��
     */
    public void init() {
        length = 4;
        snakeX[0] = 100;
        snakeY[0] = 100; //ͷ������
        snakeX[1] = 75;
        snakeY[1] = 100; //��һ������
        snakeX[2] = 50;
        snakeY[2] = 100; //�ڶ�������
        snakeX[3] = 25;
        snakeY[3] = 100; //����������
        fx = RIGHT;
        //��ʼ�����̼����¼�
        this.setFocusable(true);
        this.addKeyListener(this);
        //��ʼ����ʱ��
        timer.start();
        //��ʼ��ʳ������
        footX = EAST + EAST * random.nextInt(32);
        footY = TOP + EAST * random.nextInt(22);
        //��ʼ������
        score = 0;

    }

    /**
     * ����
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        //����
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        //���ƶ�����
        SnakeDate.header.paintIcon(this, g, EAST, 11);
        //������Ϸ����
        g.fillRect(EAST, TOP, WEAT, 600);
        //��һ��С��(��ͷ����)
        if (RIGHT.equals(fx)) {
            SnakeDate.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (LEFT.equals(fx)) {
            SnakeDate.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (UP.equals(fx)) {
            SnakeDate.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (DOWN.equals(fx)) {
            SnakeDate.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //������
        for (int i = 1; i < length; i++) {
            SnakeDate.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        //��ʳ��
        SnakeDate.food.paintIcon(this, g, footX, footY);
        //������
        g.setColor(Color.RED);
        g.setFont(new Font("΢���ź�", Font.BOLD, 18));
        g.drawString("����>> " + length, 750, 32);
        g.drawString("����>> " + score, 750, 52);
        //������
        g.setColor(Color.WHITE);
        g.setFont(new Font("΢���ź�", Font.BOLD, 40));
        g.drawString("Free Games", 335, 49);

        //��Ϸ��ʾ
        if (!isStart) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));
            g.drawString("���ո�ʼ��Ϸ", 300, 400);
        }
        //ʧ����ʾ
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));
            g.drawString("Game Over!!!", 300, 300);
            g.drawString("���ո����¿�ʼ��Ϸ", 300, 400);
        }

    }

    /**
     * ������������
     * ���̰��£�δ�ͷŴ������¼�
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //��ȡ����
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {  //��Ϸʧ�ܣ����ո����³�ʼ��
                isFail = false;
                init();
            } else {  //û��ʧ�ܣ����ո���ͣ��Ϸ
                isStart = !isStart;
            }
            repaint();//ˢ��ҳ�棬���¼������з���

        }
        //���̿������򣨲�����ֱ�ӷ����ͷ��
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
     * ��ʱ��
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //�����Ϸ���ڿ�ʼ״̬��û��ʧ��
        if (isStart && !isFail) {
            //�ƶ�����
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (RIGHT.equals(fx)) {
                snakeX[0] = snakeX[0] + EAST; //ͷ���ƶ��ͱ߽�ֵ
                if (snakeX[0] > WEAT) {
                    snakeX[0] = EAST;
                }
            } else if (LEFT.equals(fx)) {
                snakeX[0] = snakeX[0] - EAST; //ͷ���ƶ��ͱ߽�ֵ
                if (snakeX[0] < EAST) {
                    snakeX[0] = WEAT;
                }
            } else if (UP.equals(fx)) {
                snakeY[0] = snakeY[0] - EAST; //ͷ���ƶ��ͱ߽�ֵ
                if (snakeY[0] < TOP) {
                    snakeY[0] = BUTTOM;
                }
            } else if (DOWN.equals(fx)) {
                snakeY[0] = snakeY[0] + EAST; //ͷ���ƶ��ͱ߽�ֵ
                if (snakeY[0] > BUTTOM) {
                    snakeY[0] = TOP;
                }
            }

            //��ʳ�ﲢ��������
            if (snakeX[0] == footX && snakeY[0] == footY) {
                length++;
                snakeX[length - 1] = footX - 1;
                snakeY[length - 1] = footY - 1;
                score += 10;
                footX = EAST + EAST * random.nextInt(32);
                footY = TOP + EAST * random.nextInt(22);
            }
            //�Ƿ�����(�����ͷ�غ�)
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }
            repaint();
        }
        timer.start(); //��ʱ�䶯����
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //���̰��µ���
    }
}
