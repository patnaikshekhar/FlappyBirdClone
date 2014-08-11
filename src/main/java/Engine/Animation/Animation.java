package Engine.Animation;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by shpatnaik on 8/8/14.
 */
public class Animation {

    public final BufferedImage[] frames;
    protected final int noOfFrames;
    public int currentFrame;

    public Animation(BufferedImage[] frames) {
        this.frames = frames;
        this.currentFrame = 0;
        this.noOfFrames = frames.length;
    }

    public BufferedImage getNextFrame() {
        if (currentFrame >= noOfFrames) {
            currentFrame = 0;
        }

        BufferedImage thisFrame = frames[currentFrame];

        currentFrame += 1;

        return thisFrame;
    }

    public BufferedImage getNextFrameUntilEnd() {
        if (currentFrame >= noOfFrames) {
            return null;
        }

        BufferedImage thisFrame = frames[currentFrame];

        currentFrame += 1;

        return thisFrame;
    }

    public void reset() {
        currentFrame = 0;
    }
}
