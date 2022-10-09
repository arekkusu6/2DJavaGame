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
        velocity = 3;  // 3 pixels
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
        else if (handler.right) {
            direction = "right";
            x += velocity;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "left" -> left1;
            case "down" -> down1;
            case "right" -> right1;
            default -> null;
        };

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
