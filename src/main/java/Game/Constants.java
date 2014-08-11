package Game;

import Engine.Utilities.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shpatnaik on 8/8/14.
 */
public class Constants {

    // Window Constants
    public static final String WINDOW_TITLE = "Flappy Bird";
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_SCALE = 1;
    public static final int FPS = 60;

    // Sprite Sheet Constants
    public static final Map<String, Rectangle> SPRITE_MAP = new HashMap<String, Rectangle>();

    static {
        SPRITE_MAP.put("Bird Frame 1", new Rectangle(0, 490, 20, 13));
        SPRITE_MAP.put("Bird Frame 2", new Rectangle(28, 490, 20, 13));
        SPRITE_MAP.put("Bird Frame 3", new Rectangle(56, 490, 20, 13));
        SPRITE_MAP.put("Dead Bird", new Rectangle(309, 90, 20, 13));
        SPRITE_MAP.put("Ground", new Rectangle(292, 0, 168, 55));
        SPRITE_MAP.put("Sky", new Rectangle(0, 0, 144, 256));
        SPRITE_MAP.put("Logo", new Rectangle(350, 91, 91, 25));
        SPRITE_MAP.put("Game Over", new Rectangle(393, 57, 100, 25));
        SPRITE_MAP.put("Pipe Down", new Rectangle(56, 323, 26, 160));
        SPRITE_MAP.put("Pipe Up", new Rectangle(84, 323, 26, 160));
        SPRITE_MAP.put("Coin", new Rectangle(121, 282, 22, 22));
    }
    public static final String SPRITE_SHEET = "/sprites/FlappyBird.png";

    // Bird Constants
    public static final double BIRD_START_POSITION_X = 20;
    public static final double BIRD_START_POSITION_Y = WINDOW_WIDTH / 2;
    public static final int BIRD_WIDTH = 50;
    public static final int BIRD_HEIGHT = 50;
    public static final int BIRD_CHANGE_ANIMATION_TIME = 200;
    public static final Vector2 GRAVITY_VECTOR = new Vector2(0, 0.01);
    public static final int BIRD_FLY_KEY = KeyEvent.VK_SPACE;
    public static final Vector2 BIRD_FLY_VECTOR = new Vector2(0, -0.2);
    public static final int BIRD_FLAP_COOL_DOWN_TIME = 300;
    public static final int BIRD_ZORDER = 1;

    // Ground Constants
    public static final double GROUND_START_X = 0;
    public static final double GROUND_START_Y = 500;
    public static final int GROUND_WIDTH = WINDOW_WIDTH;
    public static final int GROUND_HEIGHT = 100;
    public static final double GROUND_SPEED = 0.10;
    public static final String GROUND_TYPE = "Ground";

    // Sky Constants
    public static final double SKY_START_X = 0;
    public static final double SKY_START_Y = 0;
    public static final int SKY_WIDTH = WINDOW_WIDTH;
    public static final int SKY_HEIGHT = 500;
    public static final double SKY_SPEED = 0.12;
    public static final String SKY_TYPE = "Sky";

    // Celling Constants
    public static final int CELLING_WIDTH = WINDOW_WIDTH;
    public static final int CELLING_HEIGHT = 2;
    public static final String CELLING_TYPE = "Celling";

    // Game Over Text Constants
    public static final int GAME_OVER_WIDTH = 200;
    public static final int GAME_OVER_HEIGHT = 70;
    public static final int GAME_OVER_X = (WINDOW_WIDTH / 2) - (GAME_OVER_WIDTH / 2);
    public static final int GAME_OVER_Y = (WINDOW_HEIGHT / 3) - (GAME_OVER_HEIGHT / 2);
    public static final String GAME_OVER_TYPE = "Text";
    public static final int GAME_OVER_ZORDER = 2;

    // Obstacle Constants
    public static final int OBSTACLE_DURATION = 2500;
    public static final int OBSTACLE_MAX_HEIGHT = 300;
    public static final int OBSTACLE_MIN_HEIGHT = 50;
    public static final int OBSTACLE_START_X = WINDOW_WIDTH;
    public static final double OBSTACLE_SPEED = GROUND_SPEED * 2;
    public static final int OBSTACLE_WIDTH = 100;
    public static final int OBSTACLE_GAP = 110;
    public static final int OBSTACLE_Z_ORDER = 1;

    // Game Over Text Constants
    public static final int LOGO_WIDTH = 200;
    public static final int LOGO_HEIGHT = 70;
    public static final int LOGO_X = (WINDOW_WIDTH / 2) - (GAME_OVER_WIDTH / 2);
    public static final int LOGO_Y = (WINDOW_HEIGHT / 3) - (GAME_OVER_HEIGHT / 2);
    public static final String LOGO_TYPE = "Text";
    public static final int LOGO_ZORDER = 2;
    public static final int START_KEY = KeyEvent.VK_SPACE;

    // Start Text Constants
    public static final int START_TEXT_X = (WINDOW_WIDTH / 2) - 120;
    public static final int START_TEXT_Y = 270;
    public static final String START_TEXT_TYPE = "Text";
    public static final String START_TEXT_START_TEXT = "Press Space to Start";
    public static final int START_TEXT_ZORDER = 2;
    public static final Color START_TEXT_COLOR = Color.GREEN;
    public static final Font START_TEXT_FONT = new Font("Arial", Font.PLAIN, 26);

    // Coin Constants
    public static final int COIN_WIDTH = 60;
    public static final int COIN_HEIGHT = 60;
    public static final double COIN_SPEED = OBSTACLE_SPEED;
    public static final int COIN_SCORE = 1;

    // Score Constants
    public static final int SCORE_X = WINDOW_WIDTH / 2;
    public static final int SCORE_Y = WINDOW_HEIGHT / 7;
    public static final String SCORE_TYPE = "Text";
    public static final Color SCORE_COLOR = Color.BLACK;
    public static final Font SCORE_FONT = new Font("Arial", Font.BOLD, 26);
    public static final int SCORE_ZORDER = 2;
}
