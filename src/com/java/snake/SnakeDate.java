package com.java.snake;

import javax.swing.*;
import java.net.URL;

/**
 * @author mabin
 * @date 2020/3/13 16:15
 * 引入静态资源
 */

public class SnakeDate {

    //顶部栏
    private static URL headerURL = SnakeDate.class.getResource("/static/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);

    //蛇的相关元素
    private static URL upURL = SnakeDate.class.getResource("/static/up.png");
    public static ImageIcon up = new ImageIcon(upURL);

    private static URL downURL = SnakeDate.class.getResource("/static/down.png");
    public static ImageIcon down = new ImageIcon(downURL);

    private static URL leftURL = SnakeDate.class.getResource("/static/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);

    private static URL rightURL = SnakeDate.class.getResource("/static/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);

    private static URL bodyURL = SnakeDate.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    private static URL foodURL = SnakeDate.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);


}
