package game.enemies;

import game.Utils;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

/**
 * Created by VALV on 7/24/2017.
 */
public class BossEnemySpell2 extends GameObject {
    Vector2D nexposition = new Vector2D();
//    boolean status1 = true;
//    boolean status2 = false;
//    float sum;
    public BossEnemySpell2 () {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/cyan.png"));
    }
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(nexposition);
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            recycle.add(this);

//        if (sum > 100) {
//            status1 = false;
//        }
//        if (sum > 150) {
//            status2 = true;
//        }
//
//
//        if (status1) {
//            this.position.addUp(this.nexposition);
//            sum += Math.sqrt(this.nexposition.x * this.nexposition.x + this.nexposition.y * this.nexposition.y);
//        } else if (status2 == status1) {
//            this.position.addUp(this.nexposition.x / 7, this.nexposition.y / 7);
//            sum += Math.sqrt((this.nexposition.x / 7) * (this.nexposition.x / 7) + (this.nexposition.y / 7) * (this.nexposition.y / 7));
//        } else if (status2) {
//            this.position.addUp(this.nexposition.x , this.nexposition.y );
//        }
    }
}
