package game.enemies;

import game.Utils;
import game.base.BoxCollider;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;
import game.player.Player;

import java.util.Iterator;

/**
 * Created by VALV on 7/20/2017.
 */
public class BlueEnemySpell extends GameObject {
    Vector2D velocity;
    Vector2D velocityPosition;
    BoxCollider boxCollider;
//    Vector2D lastPosition;


    public BlueEnemySpell() {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/white.png"));
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(5,5);
        this.childrens.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocityPosition = Player.instance.position;
        if (Math.sqrt(velocityPosition.substract(this.position).x * velocityPosition.substract(this.position).x +
                velocityPosition.substract(this.position).y * velocityPosition.substract(this.position).y) < 100) {
            velocity = velocityPosition.substract(this.position).normalize().multiply(3);
        }
        this.position.addUp(velocity);
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            recycle.add(this);
//        childrens.add(this.boxCollider);
//        System.out.println(this.boxCollider);
    }
}
