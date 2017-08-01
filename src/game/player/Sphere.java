package game.player;

import game.Utils;
import game.bases.*;

import game.bases.renderers.ImageRenderer;
import game.inputs.InputManager;

import java.awt.*;

/**
 * Created by levua on 7/29/2017.
 */

public class Sphere extends GameObject {
    FrameCounter changePicture;
    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;
    InputManager inputManager;
    BoxCollider boxCollider;
    FrameCounter coolDownSpell;

    public Sphere() {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("sphere/0.png"));
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("sphere/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("sphere/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("sphere/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("sphere/3.png"));
        this.changePicture = new FrameCounter(3);
        this.isActive = true;
        this.inputManager = new InputManager();
        this.coolDownSpell = new FrameCounter(15);
        this.boxCollider = new BoxCollider();
        this.children.add(this.boxCollider);


    }

    @Override
    public void changePicture() {
        super.changePicture();
        if (changePicture.run()) {
            if (renderer == imageRenderer1) renderer = imageRenderer2;
            else if (renderer == imageRenderer2) renderer = imageRenderer3;
            else if (renderer == imageRenderer3) renderer = imageRenderer4;
            else renderer = imageRenderer1;

            changePicture.reset();
        }
    }


    public void run() {

        this.position.addUp(screenPosition);
//        shoot();
    }
    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void shoot() {
//
        if (coolDownSpell.run()) {
            SphereBullet sphereBullet = GameObjectPool.recycle(SphereBullet.class);
            sphereBullet.position.set(this.screenPosition);
            coolDownSpell.reset();
        }



//        if ( coolDownSpell.run()  ) {
//            SphereBullet sphereBullet = GameObjectPool.recycle(SphereBullet.class);
//            sphereBullet.screenPosition.set(this.screenPosition);
//
//            coolDownSpell.reset();
//        }
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);

    }
}
