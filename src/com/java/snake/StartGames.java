package com.java.snake;

import javax.swing.*;

/**
 * @author mabin
 * @date 2020/3/13 16:02
 * ������Ϸ����
 */

public class StartGames {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Free Game");
        //���ڽ����С
        frame.setBounds(10,10,900,720);
        //���ڴ�С���ɱ�
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //������Ϸ���JPanel
        frame.add(new GamePanel());
        frame.setVisible(true);

    }

}
