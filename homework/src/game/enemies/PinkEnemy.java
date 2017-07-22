package game.enemies;

import game.Utils;
import game.base.FrameCounter;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

import java.util.Random;

/**
 * Created by VALV on 7/21/2017.
 */
public class PinkEnemy extends GameObject {
    FrameCounter coolDownCounter;
//    FrameCounter coolDownSpawn;
    FrameCounter coolDownspell;
    boolean stopCastSpell;
    Vector2D velocity;
    int i = 5;

    boolean move1 = true,move2,move3;

    boolean spellDissabled;
    boolean enemyDissabled;

    public PinkEnemy() {
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(50);

        this.coolDownspell = new FrameCounter(5);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/pink/1.png"));
    }

    public void spawnEnemy() {
        if (!enemyDissabled) {
            PinkEnemy pinkEnemy = new PinkEnemy();
            pinkEnemy.position.set(96,10);
            GameObject.add(pinkEnemy);
            GameObject.add(pinkEnemy);
            enemyDissabled = true;
        }
    }

    public void spawnEnemy2() {

        if (!enemyDissabled) {

            PinkEnemy pinkEnemy = new PinkEnemy();
            pinkEnemy.position.set(288,10);
            GameObject.add(pinkEnemy);
            GameObject.add(pinkEnemy);
            enemyDissabled = true;
        }
    }


    public void castSpell() {
        if (!spellDissabled) {

            for (int j = 20; j<160; j+=10) {
                PinkEnemySpell pinkEnemySpell = new PinkEnemySpell();
                pinkEnemySpell.nextPosition = new Vector2D((float)(3 * Math.cos(Math.PI * j/180)),(float)( 3 * Math.sin(j * Math.PI/180 )));
                pinkEnemySpell.position.set(this.position);
                GameObject.add(pinkEnemySpell);
                coolDownspell = new FrameCounter(200);
            }
            spellDissabled = true;

        }
    }

    public void coolDownSpell() {
        if (spellDissabled && !stopCastSpell) {
            boolean status = coolDownspell.run();

            if (status ) {
                spellDissabled = false;
                coolDownspell.reset();
            }
        }
    }


    public void run() {
        castSpell();
        coolDownSpell();

        move();
    }

    public void move() {
        if (move1) {
            this.position.addUp(0, (float)0.5);
        }
        if (move2) {
            this.position.addUp((float) -0.5,(float)0.5);
        }
        if (move3) {
            this.position.addUp((float)0.5,(float) 0.5);
        }


        if (this.position.x < 192 && this.position.y > 200) {
            move2 = true;
            move3 = false;
            move1 = false;
        }

        if (this.position.x > 192 && this.position.y > 200) {
            move3 = true;
            move1 = false;
            move2 = false;
        }


    }
}
