package game.bases.physics;

import game.bases.BoxCollider;

import java.util.Vector;

/**
 * Created by VALV on 7/25/2017.
 */
public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }


    // Generics
    public static <T extends PhysicsBody> T bodyInRect(BoxCollider boxCollider, Class<T> classz) {
        for (PhysicsBody body : bodies) {
            if (body.isActive() && body.getBoxCollider().coolidewith(boxCollider)) {
                if (body.getClass() == classz)
                    return (T) body;
            }
        }
        return null;
    }

    public static <T extends PhysicsBody> T findEnemy(Class<T> classz) {
        for (PhysicsBody body : bodies) {
            if (body.isActive() && body.getClass() == classz) {
                return (T) body;
            }
        }
        return null;
    }
    public static void clearAll() {
        bodies.clear();
    }
}
