package game.base;

/**
 * Created by VALV on 7/24/2017.
 */
public class BoxCollider extends GameObject {
    public float width;
    public float height;

    public BoxCollider (float width, float height) {
        super();
        this.width = width;
        this.height = height;
    }
    public BoxCollider () {
        this(0,0);
    }

    public boolean cooliderWith(BoxCollider other) {
        return other.width > 0 && other.height > 0 && width > 0 && height > 0
                && other.position.x < position.x + width && other.position.x + other.width > position.x
                && other.position.y < position.y + height && other.position.y + other.height > position.y;
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", position=" + position +
                ", screenPosition=" + screenPosition +
                '}';
    }
}
