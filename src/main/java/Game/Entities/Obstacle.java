package Game.Entities;

import Engine.GameObject;
import Game.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shpatnaik on 8/10/14.
 */
public class Obstacle extends GameObject {

    private final BufferedImage image;
    private boolean isMoving;

    public Obstacle(double x, double y, int width, int height, BufferedImage image, double speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image; //image.getSubimage(0, 0, image.getWidth(), (int)(image.getHeight() * ((double)height / (double)Constants.OBSTACLE_MAX_HEIGHT)));
        this.vy = -speed;
        isMoving = true;
        this.zOrder = Constants.OBSTACLE_Z_ORDER;
    }

    @Override
    public void update(int dt) {
        if (isMoving) {
            this.x += (vy * dt);
            if (this.x < -width) {
                this.game.gameObjects.remove(this);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
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
        return "Obstacle";
    }

    public void stop() {
        isMoving = false;
    }
}
