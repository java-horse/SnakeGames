package com.java.snake;

import javax.swing.*;

/**
 * @author mabin
 * @date 2020/3/13 16:02
 * 绘制游戏窗口
 */

public class StartGames {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Free Game");
        //窗口界面大小
        frame.setBounds(10,10,900,720);
        //窗口大小不可变
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //增加游戏面板JPanel
        frame.add(new GamePanel());
        frame.setVisible(true);

    }

}
