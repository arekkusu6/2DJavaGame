package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // 3x3 scale

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler handler = new KeyHandler();
    Thread gameThread;

    // Set Player Defaults
    int playerX = 100;
    int playerY = 100;
    int velocity = 3;  // 4 pixels


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(handler);
        this.setFocusable(true);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start(); // calls the run() method
    }
    @Override
    public void run() {
        int drawInterval = 1000000000/FPS;
        double delta = 0;
        float lastTime = System.nanoTime();
        long currentTime;
        while (gameThread.isAlive()){
            currentTime = System.nanoTime();

            // How much time has passed
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            // when it reaches drawInterval
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {
        if (handler.up) {
            playerY -= velocity;
        }
        else if (handler.left) {
            playerX -= velocity;
        }
        else if (handler.down) {
            playerY += velocity;
        }
        else if (handler.right) {
            playerX += velocity;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); // dispose graphics context to save memory (release sys resources)
    }
}
