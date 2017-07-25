package game.player;

import game.Utils;
import game.base.*;
import game.enemies.BlueEnemy;
import game.enemies.BossEnemy;
import game.enemies.PinkEnemy;

import java.util.Vector;

/**
 * Created by VALV on 7/20/2017.
 */
public class PlayerSpell extends GameObject {
    Vector2D velocity;
    Vector2D velocityPosition;
    BoxCollider boxCollider;

    FrameCounter changePicture;
    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;

    public PlayerSpell() {
        super();
        this.changePicture = new FrameCounter(3);
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("player-spell/a/3.png"));
        this.renderer = imageRenderer1;
        this.velocity = new Vector2D();
        this.velocityPosition = new Vector2D();
        this.boxCollider = new BoxCollider(15,15);
        this.childrens.add(this.boxCollider);
    }

    public void changePicture() {
        if (changePicture.run()) {
            if (this.renderer == imageRenderer1) this.renderer = imageRenderer2;
            else if (this.renderer == imageRenderer2) this.renderer = imageRenderer3;
            else if (this.renderer == imageRenderer3) this.renderer = imageRenderer4;
            else this.renderer = imageRenderer1;
            changePicture.reset();
        }
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);
        if (this.position.x < 0 || this.position.x > 385 || this.position.y > 600 || this.position.y < 10)
            recycle.add(this);

    }
}
