package Engine.Entities;

import Engine.GameObject;
import Engine.Utilities.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shpatnaik on 8/8/14.
 */
public class ScrollingBackground extends GameObject {

    private final BufferedImage image;
    private int imageX = 0;
    private int imageY = 0;

    private double scaleX;
    private int scaleY;

    private String type;
    private boolean stop;

    public ScrollingBackground(double x, double y, int width, int height, BufferedImage image, double speed,
                               String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.setVelocity(new Vector2(speed, 0));

        this.scaleX = (double)width / (double)image.getWidth();
        this.scaleY = height / image.getHeight();
        this.type = type;

        this.stop = false;
    }

    @Override
    public void update(int dt) {
        if (!stop) {
            imageX += (vx * dt);

            if (imageX >= image.getWidth()) {
                imageX = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image.getSubimage(imageX, imageY, (image.getWidth() - imageX), image.getHeight()),
                (int)x, (int)y, (int)((image.getWidth() - imageX) * scaleX), height, null);

        if (imageX > 0) {
            g.drawImage(image.getSubimage(0, imageY, imageX + 1, image.getHeight()),
                    (int)((image.getWidth() - imageX) * scaleX), (int)y, (int)((imageX + 1) * scaleX), height, null);
        }
    }

    @Override
    public void keyPressed(int key) {
        // Do Nothing
    }

    @Override
    public void keyReleased(int key) {
        // Do Nothing
    }

    @Override
    public String getType() {
        return type;
    }

    public void stop() {
        stop = true;
    }
}
