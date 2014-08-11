package Game;

import Engine.*;
import Engine.Animation.Animation;
import Engine.Animation.Animator;
import Engine.Entities.Boundary;
import Engine.Entities.ScrollingBackground;
import Engine.Entities.StaticImage;
import Engine.Entities.StaticText;
import Engine.Utilities.Utilities;
import Game.Entities.Bird;
import Game.Entities.Coin;
import Game.Entities.Obstacle;
import Game.Entities.Score;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shpatnaik on 8/8/14.
 */
public class FlappyBird extends Game {

    // Sprite Map
    private Map<String, BufferedImage> spriteMap;

    // Game Objects;
    private Bird bird;
    private ScrollingBackground ground;
    private ScrollingBackground sky;
    private Boundary celling;
    private StaticImage gameOverImage;
    private StaticText startOverText;
    private Score score;

    // Transitional Fields
    private int timePassed;
    private boolean isStarted = false;

    public static void main(String[] args) {
        GameWindow.show(new FlappyBird(), Constants.WINDOW_TITLE, Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT, Constants.WINDOW_SCALE, Constants.FPS);
    }

    public FlappyBird() {
        super();
    }

    @Override
    public void init() {
        try {
            this.spriteMap = Utilities.processSpriteSheet(Constants.SPRITE_SHEET,
                    Constants.SPRITE_MAP);
            ground = new ScrollingBackground(Constants.GROUND_START_X,
                    Constants.GROUND_START_Y, Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT, spriteMap.get("Ground"),
                    Constants.GROUND_SPEED, Constants.GROUND_TYPE);

            sky = new ScrollingBackground(Constants.SKY_START_X, Constants.SKY_START_Y,
                    Constants.SKY_WIDTH, Constants.SKY_HEIGHT, spriteMap.get("Sky"), Constants.SKY_SPEED,
                    Constants.SKY_TYPE);

            celling = new Boundary(0, 0, Constants.CELLING_WIDTH, Constants.CELLING_HEIGHT, Constants.CELLING_TYPE);

            StaticText startText = new StaticText(Constants.START_TEXT_X, Constants.START_TEXT_Y,
                    Constants.START_TEXT_START_TEXT,
                    Constants.START_TEXT_COLOR, Constants.START_TEXT_FONT, Constants.START_TEXT_TYPE);

            StaticImage logo = new StaticImage(Constants.LOGO_X, Constants.LOGO_Y, Constants.LOGO_WIDTH,
                    Constants.LOGO_HEIGHT, Constants.LOGO_TYPE, spriteMap.get("Logo"));

            this.gameObjects.add(ground);
            this.gameObjects.add(sky);
            this.gameObjects.add(logo);
            this.gameObjects.add(startText);

        } catch (IOException e) {
            Utilities.logErrorAndExit(e);
        }
    }

    public void reset() {
        isStarted = true;

        this.gameObjects.clear();

        ground = new ScrollingBackground(Constants.GROUND_START_X,
                Constants.GROUND_START_Y, Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT, spriteMap.get("Ground"),
                Constants.GROUND_SPEED, Constants.GROUND_TYPE);

        sky = new ScrollingBackground(Constants.SKY_START_X, Constants.SKY_START_Y,
                Constants.SKY_WIDTH, Constants.SKY_HEIGHT, spriteMap.get("Sky"), Constants.SKY_SPEED,
                Constants.SKY_TYPE);

        celling = new Boundary(0, 0, Constants.CELLING_WIDTH, Constants.CELLING_HEIGHT, Constants.CELLING_TYPE);

        bird = new Bird(setupBirdAnimator(spriteMap));

        gameOverImage = new StaticImage(Constants.GAME_OVER_X, Constants.GAME_OVER_Y, Constants.GAME_OVER_WIDTH,
                Constants.GAME_OVER_HEIGHT, Constants.GAME_OVER_TYPE, spriteMap.get("Game Over"));
        gameOverImage.hide();
        gameOverImage.setzOrder(Constants.GAME_OVER_ZORDER);

        startOverText = new StaticText(Constants.START_TEXT_X, Constants.START_TEXT_Y,
                Constants.START_TEXT_START_TEXT,
                Constants.START_TEXT_COLOR, Constants.START_TEXT_FONT, Constants.START_TEXT_TYPE);
        startOverText.hide();
        startOverText.setzOrder(Constants.START_TEXT_ZORDER);

        score = new Score(Constants.SCORE_X, Constants.SCORE_Y,
                "0",
                Constants.SCORE_COLOR, Constants.SCORE_FONT, Constants.SCORE_TYPE);

        startOverText.hide();
        startOverText.setzOrder(Constants.START_TEXT_ZORDER);

        this.gameObjects.add(ground);
        this.gameObjects.add(sky);
        this.gameObjects.add(bird);
        this.gameObjects.add(gameOverImage);
        this.gameObjects.add(startOverText);
        this.gameObjects.add(score);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

    @Override
    public void update(int dt) {
        super.update(dt);

        if (dt > 0 && isStarted) {

            // Check Collisions
            if (ground.getRectangle().intersects(bird.getRectangle())) {
                bird.setPosition(bird.getX(), ground.getY() - bird.getHeight());
                gameOver();
            }

            if (celling.getRectangle().intersects(bird.getRectangle())) {
                bird.setPosition(bird.getX(), celling.getY() + celling.getHeight());
            }

            for (GameObject o : this.gameObjects.getObjectsOfType("Obstacle")) {
                if (o.getRectangle().intersects(bird.getRectangle())) {

                    if (bird.getX() + bird.getWidth() >= o.getX()) {
                        bird.setPosition(o.getX() - bird.getWidth(), o.getY());
                    }

                    if (bird.getY() + bird.getHeight() >= o.getY()) {
                        bird.setPosition(bird.getX(), o.getY() - bird.getHeight());
                    }

                    gameOver();
                }
            }

            for (GameObject o : this.gameObjects.getObjectsOfType("Coin")) {
                if (o.getRectangle().intersects(bird.getRectangle())) {
                    score.increment(Constants.COIN_SCORE);
                    this.gameObjects.remove(o);
                }
            }

            // Generate Obstacles
            if (!bird.isDead()) {
                timePassed += dt;
                if (timePassed > Constants.OBSTACLE_DURATION) {
                    generateObstacle();
                    timePassed = 0;
                }
            }
        }
    }

    private void gameOver() {
        ground.stop();
        sky.stop();
        bird.die();
        for (GameObject o : this.gameObjects.getObjectsOfType("Obstacle")) {
            ((Obstacle)o).stop();
        }

        for (GameObject o : this.gameObjects.getObjectsOfType("Coin")) {
            ((Coin)o).stop();
        }

        gameOverImage.show();
        startOverText.show();
    }

    @Override
    public void keyPressed(int key) {
        super.keyPressed(key);

        if ((!isStarted || bird.isDead()) && key == Constants.START_KEY) {
            reset();
        }
    }

    @Override
    public void keyReleased(int key) {
        super.keyReleased(key);
    }

    public Animator setupBirdAnimator(Map<String, BufferedImage> spriteMap) {

        Animation birdAnimation = new Animation(new BufferedImage[]{
                spriteMap.get("Bird Frame 1")});

        Animation birdFlapAnimation = new Animation(new BufferedImage[]{
                spriteMap.get("Bird Frame 2"),
                spriteMap.get("Bird Frame 3"),
                spriteMap.get("Bird Frame 1")});

        Animation birdDeadAnimation = new Animation(new BufferedImage[]{
                spriteMap.get("Dead Bird")});

        Map<String, Animation> animationStates = new HashMap<String, Animation>();
        animationStates.put("Normal", birdAnimation);
        animationStates.put("Flap", birdFlapAnimation);
        animationStates.put("Dead", birdDeadAnimation);

        return new Animator(animationStates, "Normal");
    }

    private void generateObstacle() {

        int topPipeHeight = Utilities.randomNumber(Constants.OBSTACLE_MIN_HEIGHT, Constants.OBSTACLE_MAX_HEIGHT);
        int bottomPipeHeight = Constants.WINDOW_HEIGHT - (topPipeHeight + Constants.OBSTACLE_GAP);

        Obstacle topPipe = new Obstacle(Constants.OBSTACLE_START_X, 0,
                Constants.OBSTACLE_WIDTH, topPipeHeight, this.spriteMap.get("Pipe Down"), Constants.OBSTACLE_SPEED);

        Obstacle bottomPipe = new Obstacle(Constants.OBSTACLE_START_X, Constants.WINDOW_HEIGHT - bottomPipeHeight,
                Constants.OBSTACLE_WIDTH, bottomPipeHeight, this.spriteMap.get("Pipe Up"), Constants.OBSTACLE_SPEED);

        Coin coin = new Coin(Constants.OBSTACLE_START_X + (Constants.OBSTACLE_WIDTH / 2) - (Constants.COIN_WIDTH / 2),
                topPipeHeight + (Constants.OBSTACLE_GAP / 2) - (Constants.COIN_HEIGHT / 2),
                this.spriteMap.get("Coin"));

        this.gameObjects.add(topPipe);
        this.gameObjects.add(bottomPipe);
        this.gameObjects.add(coin);
    }
}
