package game.player;

import game.Utils;
import game.bases.*;
import game.bases.physics.Physics;
import game.bases.renderers.ImageRenderer;
import game.enemies.BlueEnemy;
import game.enemies.BossEnemy;
import game.enemies.PinkEnemy;
import game.screnes.Settings;
import tklibs.AudioUtils;

/**
 * Created by levua on 7/29/2017.
 */
public class SphereBullet extends GameObject {
    public Vector2D velocity;
    BoxCollider boxCollider;
    public static int damage;
    private Vector2D target;
    FrameCounter frameCounter;
    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;

    public SphereBullet() {
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(15,15);
        damage = 3;
        this.children.add(this.boxCollider);
        this.target = new Vector2D();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("sphere-bullets/0.png"));
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("sphere-bullets/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("sphere-bullets/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("sphere-bullets/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("sphere-bullets/3.png"));
        this.frameCounter = new FrameCounter(3);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.velocity.set(0,-9);
        this.position.addUp(velocity);
        changePicture();
        hitEnemy();
        if (this.position.y < 0 || this.position.x > Settings.gameplaywidth || this.position.x < 0 ) this.isActive = false;
    }

    @Override
    public void changePicture() {
        super.changePicture();
        if (frameCounter.run()) {
            if (this.renderer == imageRenderer1) this.renderer = imageRenderer2;
            else if (this.renderer == imageRenderer2) this.renderer = imageRenderer3;
            else if (this.renderer == imageRenderer3) this.renderer = imageRenderer4;
            else this.renderer = imageRenderer1;
            frameCounter.reset();
        }
    }

    public void hitEnemy() {
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
    }
}
