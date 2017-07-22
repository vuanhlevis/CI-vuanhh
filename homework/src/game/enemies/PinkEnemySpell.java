package game.enemies;

import game.Utils;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

/**
 * Created by VALV on 7/21/2017.
 */
public class PinkEnemySpell extends GameObject {
    Vector2D nextPosition = new Vector2D();
    public PinkEnemySpell () {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/green.png"));
    }
//    public void move(float dx, float dy) {
//        this.position.addUp(dx,dy);
//    }

    public void run() {
        this.position.addUp(this.nextPosition);
    }

}
