package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler handler;

    public Player(GamePanel gp, KeyHandler handler) {
        this.gp = gp;
        this.handler = handler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        velocity = 2;  // 2 pixels
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(handler.up || handler.down || handler.left || handler.right) {
            if (handler.up) {
                direction = "up";
                y -= velocity;
            }
            else if (handler.left) {
                direction = "left";
                x -= velocity;
            }
            else if (handler.down) {
                direction = "down";
                y += velocity;
            }
            else {
                direction = "right";
                x += velocity;
            }
            // Animation for moving. Update is 60 FPS. Every frame will increase counter by 1.
            // if it hits 10, it changes the sprite
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
