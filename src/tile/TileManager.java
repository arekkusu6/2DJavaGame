package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("res/tiles/water.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("res/tiles/grass.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("res/tiles/wall.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {
            InputStream inst = getClass().getResourceAsStream("tile-maps/tilemap.txt");
            assert inst != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inst));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = reader.readLine(); // get .txt numbers one by one
                while(col < gp.maxScreenCol) {
                    String[] numbers = line.split(" "); // into numbers
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row]; // map data stored here in mapTileNum
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
