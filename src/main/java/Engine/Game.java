package Engine;

import Engine.Managers.GameObjectManager;
import Engine.Managers.SoundManager;

import java.awt.*;

/**
 * Created by shpatnaik on 8/3/14.
 */
public abstract class Game {

    protected int width;
    protected int height;

    protected GameObjectManager gameObjects;
    protected SoundManager gameSounds;

    public Game() {
        gameObjects = new GameObjectManager();
        gameSounds = new SoundManager();
    }

    public abstract void init();

    public void draw(Graphics2D g) {
        gameObjects.draw(g);
    }

    public void update(int dt) {
        gameObjects.update(dt);
    }

    public void keyPressed(int key) {
        gameObjects.keyPressed(key);
    }

    public void keyReleased(int key) {
        gameObjects.keyReleased(key);
    }
}
