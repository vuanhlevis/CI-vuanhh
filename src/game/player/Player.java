package game.player;

import game.Utils;
import game.bases.*;
import game.bases.physics.PhysicsBody;
import game.bases.renderers.ImageRenderer;
import game.inputs.InputManager;
import tklibs.AudioUtils;

import javax.swing.*;

/**
 * Created by VALV on 7/11/2017.
 */
//
public class Player extends GameObject implements PhysicsBody {
    public int HP;
    Sphere sphereLeft;
    Sphere sphereRight;
    Contraints contraints;
    InputManager inputManager;

    FrameCounter coolDownCounter;
    boolean spellDisabled;
    BoxCollider boxCollider;
    Vector2D velocity;


    public static Player instance;

    public Player() {
        super();
        this.boxCollider = new BoxCollider(5, 5);
        this.HP = 100;
        this.velocity = new Vector2D();
        this.coolDownCounter = new FrameCounter(5);  // 17 frame  = 300 millisecond
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        instance = this;
        children.add(boxCollider);

        this.sphereRight = new Sphere();
        this.sphereLeft = new Sphere();
        sphereLeft.position.set(-15,0);
        sphereRight.position.set(15,0);

        this.children.add(sphereRight);
        this.children.add(sphereLeft);

        GameObject.add(sphereLeft);
        GameObject.add(sphereRight);

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        move();
        sphereLeft.shoot();
        sphereRight.shoot();
        castSpell();

//        System.out.println(this.sphereLeft.screenPosition);
    }

    private void castSpell() {
        if (!spellDisabled) {
            if (inputManager.xPress) {
                AudioUtils.playMedia("assets/music/sfx/player-shoot.wav");
                PlayerSpell playerSpell = GameObjectPool.recycle(PlayerSpell.class);
                playerSpell.position.set(this.position.add(0, -20));
            }
            spellDisabled = true;
        }
        coolDown();
    }

    private void move() {
        this.velocity.set(0, 0);
        if (inputManager.leftPress) this.velocity.x -= 10;
        if (inputManager.rightPress) this.velocity.x += 10;
        if (inputManager.upress) this.velocity.y -= 10;
        if (inputManager.downPress) this.velocity.y += 10;

        this.position.addUp(velocity);

        this.contraints.make(this.position);

    }

    public void coolDown() {
        if (spellDisabled) {
            boolean status = coolDownCounter.run();
            if (status) {
                spellDisabled = false;
                coolDownCounter.reset();
            }
        }
    }

    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
        sphereRight.setInputManager(inputManager);
        sphereLeft.setInputManager(inputManager);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
