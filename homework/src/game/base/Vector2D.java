package game.base;

/**
 * Created by VALV on 7/20/2017.
 */
public class Vector2D {
    public float x;
    public float y;

    public double a;
    public double b;
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0,0);
    }

    public void addUp(float x, float y) {
        this.x += x;
        this.y += y;
    }
    public void addUp(Vector2D other) {
        addUp(other.x,other.y);
    }

    public Vector2D add(float x, float y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x,other.y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        set(other.x,other.y);
    }

    public void set1(double a, double b) {
        this.a = a;
        this.b = b;
    }
    public void set1(Vector2D other) {
        set1(other.a,other.b);
    }


    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
