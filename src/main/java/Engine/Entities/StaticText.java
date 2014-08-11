package Engine.Entities;

import Engine.GameObject;

import java.awt.*;

/**
 * Created by shpatnaik on 8/11/14.
 */
public class StaticText extends GameObject {

    protected String text;
    protected final Color color;
    protected final Font font;
    protected final String type;
    protected boolean display;

    public StaticText(int x, int y, String text, Color color, Font font, String type) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.font = font;
        this.type = type;
        this.display = true;
    }


    @Override
    public void update(int dt) {
        // Do Nothing
    }

    @Override
    public void draw(Graphics2D g) {
        if (display) {
            g.setColor(color);
            g.setFont(font);
            g.drawString(text, (int)x, (int)y);
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

    public void show() {
        display = true;
    }

    public void hide() {
        display = false;
    }
}
