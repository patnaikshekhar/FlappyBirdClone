package Engine.Entities;

import Engine.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shpatnaik on 8/10/14.
 */
public class StaticImage extends GameObject {
    protected String type;
    protected BufferedImage image;
    protected boolean isVisible;

    public StaticImage(double x, double y, int width, int height, String type, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.type = type;
        isVisible = true;
    }

    @Override
    public void update(int dt) {
        // Do Nothing
    }

    @Override
    public void draw(Graphics2D g) {
        if (isVisible) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {
        // Do Nothing
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }
}
