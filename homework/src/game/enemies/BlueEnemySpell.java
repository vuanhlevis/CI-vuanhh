package game.enemies;

import game.Utils;
import game.base.GameObject;
import game.base.ImageRenderer;

import java.util.Iterator;

/**
 * Created by VALV on 7/20/2017.
 */
public class BlueEnemySpell extends GameObject{

    public BlueEnemySpell() {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/bullets/cyan.png"));
    }
    public void run() {

        this.position.addUp(0,6);

    }
}
