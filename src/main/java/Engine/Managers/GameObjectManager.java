package Engine.Managers;

import Engine.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by shpatnaik on 8/3/14.
 */
public class GameObjectManager {

    private List<GameObject> objects;

    public GameObjectManager() {
        objects = new ArrayList<GameObject>();
    }

    public void update(int dt) {
        for (GameObject object : objects) {
            object.update(dt);
        }
    }

    public void draw(Graphics2D g) {
        List<GameObject> objectsCopied = (List<GameObject>)((ArrayList<GameObject>) objects).clone();
        for (GameObject object : objectsCopied) {
            object.draw(g);
        }
    }

    public void keyPressed(int key) {
        List<GameObject> objectsCopied = (List<GameObject>)((ArrayList<GameObject>) objects).clone();
        for (GameObject object : objectsCopied) {
            object.keyPressed(key);
        }
    }

    public void keyReleased(int key) {
        List<GameObject> objectsCopied = (List<GameObject>)((ArrayList<GameObject>) objects).clone();
        for (GameObject object : objectsCopied) {
            object.keyPressed(key);
        }
    }

    public void add(GameObject object) {
        objects.add(object);

        // Set the parent game manager on the object
        object.setGameObjectManager(this);

        sortObjects();
    }

    private void sortObjects() {
        // Sort the objects by z-order
        Collections.sort(objects, new ZOrderComparator());
    }

    public void remove(GameObject object) {
        objects.remove(object);
    }

    public void clear() {
        objects.clear();
    }

    public boolean contains(String type) {
        for (GameObject object : objects) {
            if (object.getType().compareTo(type) == 0) {
                return true;
            }
        }

        return false;
    }

    public List<GameObject> getObjectsOfType(String type) {
        List<GameObject> results = new ArrayList<GameObject>();

        for (GameObject object : objects) {
            if (object.getType().compareTo(type) == 0) {
                results.add(object);
            }
        }

        return results;
    }

    class ZOrderComparator implements Comparator<GameObject> {

        @Override
        public int compare(GameObject o1, GameObject o2) {
            return o1.getzOrder() - o2.getzOrder();
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
