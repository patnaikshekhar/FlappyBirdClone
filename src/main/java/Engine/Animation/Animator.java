package Engine.Animation;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by shpatnaik on 8/9/14.
 */
public class Animator {

    private Map<String, Animation> states;
    private String defaultState;
    private String currentState;
    private boolean infiniteTransitions;
    private String nextState;

    public Animator(Map<String, Animation> states, String defaultState) {
        this.states = states;
        this.defaultState = defaultState;
        this.currentState = defaultState;
        infiniteTransitions = true;
    }

    public BufferedImage getNextFrame() {
        if (infiniteTransitions) {
            return this.states.get(currentState).getNextFrame();
        } else {
            BufferedImage nextFrame = this.states.get(currentState).getNextFrameUntilEnd();
            if (nextFrame == null) {
                this.states.get(currentState).reset();
                currentState = nextState;
                infiniteTransitions = true;
                return this.states.get(currentState).getNextFrame();
            } else {
                return nextFrame;
            }
        }
    }

    public void transition(String state) {
        currentState = state;
    }

    public void transitionAndBack(String state) {
        nextState = defaultState;
        currentState = state;
        infiniteTransitions = false;
    }

    public void transitionAndBack(String state, String finalState) {
        nextState = finalState;
        currentState = state;
        infiniteTransitions = false;
    }
}
