package Engine.Entities;

import Engine.GameObject;

import java.awt.*;

/**
 * Created by shpatnaik on 8/10/14.
 */
public class Boundary extends GameObject {

    private Color color;
    private String type;
    private Boolean draw = false;

    public Boundary(double x, double y, int width, int height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.draw = false;
    }

    public Boundary(double x, double y, int width, int height, String type, Color color) {
        this(x, y, width, height, type);
        this.draw = true;
        this.color = color;
    }

    @Override
    public void update(int dt) {
        // Do Nothing
    }

    @Override
    public void draw(Graphics2D g) {
        if (draw) {
            g.setColor(color);
            g.fillRect((int)x, (int)y, width, height);
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
}
