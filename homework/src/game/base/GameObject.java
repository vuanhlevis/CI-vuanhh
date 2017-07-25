package game.base;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by VALV on 7/20/2017.
 */
public class GameObject {
    public Vector2D position; // relative
    public Vector2D screenPosition; //
    public ImageRenderer renderer;
    public BoxCollider boxCollider;

    public static Vector<GameObject> gameObjects = new Vector<>();
    public static Vector<GameObject> newgameObjects = new Vector<>();
    public static Vector<GameObject> recycle = new Vector<>();
    public static Vector<GameObject> enemies = new Vector<>();
    public Vector<GameObject> childrens;

    public static void add(GameObject gameObject) {
        newgameObjects.add(gameObject);
    }

    public static void renderAll(Graphics2D graphics2D) {
        for (GameObject gameObject : gameObjects) {
            gameObject.render(graphics2D);
        }
    }

    public static void runAll() {

        for (GameObject gameObject : gameObjects) {
            gameObject.run(Vector2D.ZERO);
        }
        gameObjects.addAll(newgameObjects);
        gameObjects.removeAll(recycle);
        recycle.clear();
        newgameObjects.clear();

    }

    public static void changeAllPicture() {
        for (GameObject gameObject : gameObjects) {
            gameObject.changePicture();
        }
    }

    public void changePicture() {

    }

    public void run(Vector2D parenPosition) {
        this.screenPosition = parenPosition.add(position);

        for (GameObject child : childrens) {
            child.run(screenPosition);
        }
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, this.position);
        }
    }

    public GameObject() {
        this.position = new Vector2D();
        this.screenPosition = new Vector2D();
        this.childrens = new Vector<>();
        this.renderer = null;
    }
}
