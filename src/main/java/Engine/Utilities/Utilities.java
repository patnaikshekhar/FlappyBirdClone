package Engine.Utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shpatnaik on 8/5/14.
 */
public class Utilities {

    public static Map<String, BufferedImage> processSpriteSheet(String fileName, Map<String, Rectangle> tags)
            throws IOException {

        // Load base image
        BufferedImage spriteSheet = ImageIO.read(Utilities.class.getResourceAsStream(fileName));

        Map<String, BufferedImage> result = new ConcurrentHashMap<String, BufferedImage>();

        for (String tag: tags.keySet()) {
            Rectangle r = tags.get(tag);
            BufferedImage subImage = spriteSheet.getSubimage(r.x, r.y, r.width, r.height);
            result.put(tag, subImage);
        }

        return result;
    }

    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static void logErrorAndExit(Exception e) {
        System.out.println(e.getStackTrace());
        System.exit(0);
    }
}
