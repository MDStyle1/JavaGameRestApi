package com.mds.gameserver.windows;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private GameField game_field;

    public Window() throws HeadlessException {
        settingWindowStart();
    }

    private void settingWindowStart(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(200,100);
        setSize(400,400);
        setResizable(false);
        setVisible(true);
        game_field = new GameField();
        add(game_field);
    }
    private void onRepaint(Graphics g){
    }

    private class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
