package game.enemies;

import game.Utils;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

/**
 * Created by VALV on 7/22/2017.
 */
public class BossEnemySpell extends GameObject {
    Vector2D nexposition = new Vector2D();
    public BossEnemySpell () {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/pink.png"));
    }

    public void run() {
        this.position.addUp(this.nexposition);
    }

}
