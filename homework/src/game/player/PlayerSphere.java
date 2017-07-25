package game.player;

import game.Utils;
import game.base.Contraints;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

/**
 * Created by VALV on 7/25/2017.
 */
public class PlayerSphere extends GameObject {
    Vector2D velocity;

    public PlayerSphere () {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("sphere/0.png"));
        this.velocity = Player.instance.velocity;
    }

    public void addSphere(Vector2D position) {

        GameObject.add(this);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
    }
}
