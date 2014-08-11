package Game.Entities;

import Engine.Entities.StaticImage;
import Game.Constants;

import java.awt.image.BufferedImage;

/**
 * Created by shpatnaik on 8/11/14.
 */
public class Coin extends StaticImage {

    public boolean stop;

    public Coin(int x, int y, BufferedImage image) {
        super(x, y, Constants.COIN_WIDTH, Constants.COIN_HEIGHT, "Coin", image);
        this.vx = -Constants.COIN_SPEED;
        this.stop = false;
    }

    @Override
    public void update(int dt) {
        if (!stop) {
            x += (vx * dt);
        }
    }

    public void stop() {
        stop = true;
    }
}
