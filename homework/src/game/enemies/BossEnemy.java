package game.enemies;

import game.Utils;
import game.base.FrameCounter;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

/**
 * Created by VALV on 7/22/2017.
 */
public class BossEnemy extends GameObject {
    private FrameCounter coolDownSpel;
    private boolean spellDissabled;
    private Vector2D velocity;
    boolean tmp = true;
    int i = 0;
    int stopSpell = 30;
    boolean left, right, down = true;


    public BossEnemy() {
        this.coolDownSpel = new FrameCounter(2);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("enemies/level0/black/1.png"));
        this.velocity = new Vector2D();
    }

    public void spawnEnemyBoss() {
        BossEnemy enemyBoss = new BossEnemy();
        enemyBoss.position.set(192,10);
        GameObject.add(enemyBoss);
    }

    public void run() {
        move();
        castSpell();
    }

    public void castSpell() {
        if (!spellDissabled) {
            if (tmp) {
                BossEnemySpell bossEnemySpell = new BossEnemySpell();
                bossEnemySpell.nexposition = new Vector2D((float)(5*Math.cos(Math.PI*i/18)),(float)(5*Math.sin(Math.PI*i/18)));
                bossEnemySpell.position.set(this.position);
                GameObject.add(bossEnemySpell);
                i++;
                if (i > 36) {
                    i = 0;
                    tmp = false;
                    coolDownSpel = new FrameCounter(50);
                }
            }
            else {

                for (int j = 0; j<360; j+=10) {
                    BossEnemySpell bossEnemySpell = new BossEnemySpell();
                    bossEnemySpell.nexposition = new Vector2D((float) (5*Math.cos(Math.PI*j/180) ),(float)(5 * Math.sin(Math.PI*j/180)));
                    bossEnemySpell.position.set(this.position);
                    GameObject.add(bossEnemySpell);
                }

                tmp = true;
                coolDownSpel = new FrameCounter(2);
            }

            spellDissabled = true;
//            stopSpell --;
        }
        else {
            boolean status = coolDownSpel.run();
            if (status) {
                spellDissabled = false;
                coolDownSpel.reset();
            }
        }
    }
    boolean status = true;

    public void move() {
        if (this.position.y >= 150 && status) {
            left = true;
            down = false;
            right = false;
            status = false;
        }

        if (this.position.x >= 350) {
            left = true;
            right = false;
            down = false;
        }
        if (this.position.x <= 30) {
            left = false;
            right = true;
            down = false;
        }

        if (left) {
            this.position.addUp(-2,0);
        }
        if (right) {
            this.position.addUp(2,0);
        }
        if (down) {
            this.position.addUp(0,2);
        }
    }
}

