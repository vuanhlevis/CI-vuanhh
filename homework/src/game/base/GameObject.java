package game.base;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by VALV on 7/20/2017.
 */
public class GameObject {
    public Vector2D position;
    public ImageRenderer renderer;
    public static Vector<GameObject> gameObjects = new Vector<>();
    public static Vector<GameObject> newgameObjects = new Vector<>();
    public static void add(GameObject gameObject) {
        newgameObjects.add(gameObject);
    }

    public static void renderAll(Graphics2D graphics2D) {
        for (GameObject gameObject : gameObjects) {
            gameObject.render(graphics2D);
        }
    }

    public static void runAll(){
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject.position.x > 384 || gameObject.position.x < -1 || gameObject.position.y > 600 || gameObject.position.y < 10) iterator.remove();
        }
        for (GameObject gameObject : gameObjects) {
            gameObject.run();
        }

        gameObjects.addAll(newgameObjects);

        newgameObjects.clear();
    }


    public void run() {

    }

    public void render(Graphics2D g2d) {
        if (renderer!= null) {
            renderer.render(g2d,this.position);
        }
    }
    public GameObject() {
        this.position = new Vector2D();
        this.renderer = null;
    }
}
