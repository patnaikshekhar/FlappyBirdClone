package Game.Entities;

import Engine.Entities.StaticText;
import Game.Constants;

import java.awt.*;

/**
 * Created by shpatnaik on 8/11/14.
 */
public class Score extends StaticText {

    public Score(int x, int y, String text, Color color, Font font, String type) {
        super(x, y, text, color, font, type);
        zOrder = Constants.SCORE_ZORDER;
    }

    public void increment(int n) {
        text = String.valueOf(Integer.valueOf(text) + n);
    }
}
