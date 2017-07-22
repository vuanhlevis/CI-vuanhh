package game.enemies;

import game.Utils;
import game.base.FrameCounter;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by VALV on 7/20/2017.
 */
public class BlueEnemy extends GameObject{
    FrameCounter coolDownspawE;
    FrameCounter coolDownCounter;
    boolean spellDissabled;
    boolean enemyDissabled;
    Vector2D velocity;


    public BlueEnemy() {
        this.velocity = new Vector2D();
        this.coolDownspawE = new FrameCounter(150);
        this.coolDownCounter = new FrameCounter(50);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/blue/0.png"));

    }

    public void spawEnemy() {
        if (!enemyDissabled) {
            Random random = new Random();
            float rand = random.nextFloat() * (350 - 20) + 20;
            position.set(rand,10);
            GameObject.add(this);
            enemyDissabled = true;
        }
    }

    public void coolDownspawn() {
        if (enemyDissabled) {
            boolean status = coolDownspawE.run();
            if (status) {
                enemyDissabled = false;
                coolDownspawE.reset();
            }
        }
    }

    public void castSpell() {
        if (!spellDissabled) {

            BlueEnemySpell blueEnemySpell = new BlueEnemySpell();
            blueEnemySpell.position.set(this.position.add(0,10));
            GameObject.add(blueEnemySpell);
            spellDissabled = true;
        }
    }

    public void coolDownSpell() {
        if (spellDissabled) {
            boolean status = coolDownCounter.run();
            if (status) {
                spellDissabled = false;
                coolDownCounter.reset();
            }
        }
    }

    public void run() {
        castSpell();
        coolDownSpell();
        move();

    }

    public void move() {
        this.velocity.set(0,2);
        this.position.addUp(velocity);

    }

}
