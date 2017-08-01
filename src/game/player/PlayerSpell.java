package game.player;

import game.Utils;
import game.bases.*;
import game.bases.renderers.ImageRenderer;
import game.bases.physics.Physics;
import game.bases.physics.PhysicsBody;
import game.enemies.BlueEnemy;
import game.enemies.BossEnemy;
import game.enemies.PinkEnemy;
import tklibs.AudioUtils;

/**
 * Created by VALV on 7/11/2017.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {
    //properties : thuoc tinh
    public static int damage;
    private BoxCollider boxCollider;

    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;
    FrameCounter frameCounter;

    public PlayerSpell() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spell/a/0.png"));
        this.damage = 5;
        boxCollider = new BoxCollider(20, 20);
        children.add(boxCollider);

        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/3.png"));
        this.frameCounter = new FrameCounter(3);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(0, -10);
        hitEnemy();
        if (this.position.y < 0) {
            this.isActive = false;
        }
    }

    public void changePicture() {
        if (frameCounter.run()) {
            if (this.renderer == imageRenderer1) this.renderer = imageRenderer2;
            else if (this.renderer == imageRenderer2) this.renderer = imageRenderer3;
            else if (this.renderer == imageRenderer3) this.renderer = imageRenderer4;
            else this.renderer = imageRenderer1;
            frameCounter.reset();
        }
    }

    private void hitEnemy() {
        BlueEnemy hitEnemy = Physics.bodyInRect(this.boxCollider, BlueEnemy.class);
        if (hitEnemy != null) {
            hitEnemy.HP -= this.damage;
            this.isActive = false;
            if (hitEnemy.HP <= 0) {
                AudioUtils.playMedia("assets/music/sfx/enemy-explosion.wav");
                PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
                playerSpellExplove.isActive = true;
                playerSpellExplove.position = this.screenPosition;
                Items items = GameObjectPool.recycle(Items.class);
                items.position.set(this.position);
                hitEnemy.isActive = false;
            }

        }

        BossEnemy bossEnemy = Physics.bodyInRect(this.boxCollider, BossEnemy.class);
        if (bossEnemy != null) {
            bossEnemy.HP -= damage;
            this.isActive = false;
            if (bossEnemy.HP <= 0) {
                bossEnemy.HP = 0;
                AudioUtils.playMedia("assets/music/sfx/enemy-explosion.wav");
                PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
                playerSpellExplove.isActive = true;
                playerSpellExplove.position = this.screenPosition;
                bossEnemy.isActive = false;
            }

        }

        PinkEnemy pinkEnemy = Physics.bodyInRect(this.boxCollider, PinkEnemy.class);
        if (pinkEnemy != null) {
            pinkEnemy.HP -= damage;
            this.isActive = false;
            if (pinkEnemy.HP <= 0) {

                Items items = GameObjectPool.recycle(Items.class);
                items.power = 2;
                items.position.set(this.position);
                PlayerSpellExplove playerSpellExplove = GameObjectPool.recycle(PlayerSpellExplove.class);
                playerSpellExplove.isActive = true;
                playerSpellExplove.position = this.screenPosition;
                pinkEnemy.isActive = false;
            }

        }
//        System.out.println(hitEnemy);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
