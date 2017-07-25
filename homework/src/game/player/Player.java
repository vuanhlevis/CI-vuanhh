package game.player;

import game.base.*;
import game.Utils;
import game.imputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by VALV on 7/20/2017.
 */
public class Player extends GameObject {
    Contraints contraints;
    InputManager inputManager;
    FrameCounter cooldownCounter;
    boolean spellDissabled;
    public static Player instance;
    BoxCollider boxCollider;
    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;
    FrameCounter changePicture;

    Vector2D velocity;

    public Player() {
        super();
        this.velocity = new Vector2D();
        this.cooldownCounter = new FrameCounter(2);
        instance = this;
        this.boxCollider = new BoxCollider(5, 5);
        childrens.add(boxCollider);
        this.imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));
        this.imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("players/straight/1.png"));
        this.imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("players/straight/2.png"));
        this.imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("players/straight/3.png"));
        this.changePicture = new FrameCounter(5);
        this.renderer = imageRenderer1;


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
        move();
        castSpell();
        coolDown();
//        addSphere();

//        System.out.println(this.boxCollider);
    }

//    private void addSphere() {
//        PlayerSphere playerSphere1 = new PlayerSphere();
//        playerSphere1.position.set(this.position.x + 17, this.position.y);
//        playerSphere1.velocity.set(this.velocity);
//        GameObject.add(playerSphere1);
//        this.contraints.make(playerSphere1.position);
//
//        PlayerSphere playerSphere2 = new PlayerSphere();
//        playerSphere2.position.set(this.position.x - 17, this.position.y);
//        playerSphere2.velocity.set(this.velocity);
//        this.contraints.make(playerSphere2.position);
//        GameObject.add(playerSphere2);
//    }


    private void castSpell() {
        if (inputManager.xpressed && !spellDissabled) {
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.velocity.set(0, -10);
            playerSpell.position.set(this.position.add(0, -20));
            GameObject.add(playerSpell);
            spellDissabled = true;
        }
    }

    private void move() {
        this.velocity.set(0, 0);
        if (inputManager.leftpressed) this.velocity.x -= 8;
        if (inputManager.rightpressed) this.velocity.x += 8;
        if (inputManager.downpressed) this.velocity.y += 8;
        if (inputManager.uppressed) this.velocity.y -= 8;
        this.position.addUp(velocity);
        this.contraints.make(this.position);


    }

    public void coolDown() {
        if (spellDissabled) {
            boolean status = cooldownCounter.run();
            if (status) {
                spellDissabled = false;
                cooldownCounter.reset();
            }
        }
    }

    public void setContraints(Contraints contraints) {
        this.contraints = contraints;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

}
