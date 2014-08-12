package Game.Entities;

import Engine.Animation.Animation;
import Engine.Animation.Animator;
import Engine.GameObject;
import Game.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by shpatnaik on 8/8/14.
 */
public class Bird extends GameObject {

    private int currentTime;
    private Animator animator;
    private BufferedImage image;
    private int timeSinceLastFlap;

    private boolean isDead;

    public Bird(Animator animator) {
        this.x = Constants.BIRD_START_POSITION_X;
        this.y = Constants.BIRD_START_POSITION_Y;
        this.width = Constants.BIRD_WIDTH;
        this.height = Constants.BIRD_HEIGHT;
        this.animator = animator;
        this.currentTime = 0;
        this.image = this.animator.getNextFrame();
        this.isDead = false;
        this.zOrder = Constants.BIRD_ZORDER;
    }

    @Override
    public void update(int dt) {

        timeSinceLastFlap += dt;

        // Animation
        currentTime += dt;
        if (currentTime >= Constants.BIRD_CHANGE_ANIMATION_TIME) {
            image = this.animator.getNextFrame();
            currentTime = 0;
        }

        this.setVelocity(this.getVelocity().add(Constants.GRAVITY_VECTOR));

        this.x += (vx * dt);
        this.y += (vy * dt);
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void keyPressed(int key) {
        if (key == Constants.BIRD_FLY_KEY && !isDead) {
            if (timeSinceLastFlap >= Constants.BIRD_FLAP_COOL_DOWN_TIME) {
                this.setVelocity(Constants.BIRD_FLY_VECTOR);
                this.animator.transitionAndBack("Flap");
                timeSinceLastFlap = 0;
                this.game.gameSounds.playSound(Constants.BIRD_FLAP_SOUND, 0);
            }
        }
    }

    @Override
    public void keyReleased(int key) {
        // Do Nothing
    }

    @Override
    public String getType() {
        return "Bird";
    }

    public void die() {
        isDead = true;
        this.animator.transition("Dead");
    }

    public boolean isDead() {
        return isDead;
    }
}
