package game.player;

import game.base.*;
import game.Utils;
import game.imputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by VALV on 7/20/2017.
 */
public class Player extends GameObject{
    Contraints contraints;
    InputManager inputManager;
    FrameCounter cooldownCounter;
    boolean spellDissabled;

    Vector2D velocity;

    public Player() {
        this.velocity = new Vector2D();
        this.cooldownCounter = new FrameCounter(2);
        this.renderer = new ImageRenderer(Utils.loadAssetImage("players/straight/0.png"));

    }



    public void run() {
        move();
        castSpell();
        coolDown();
    }


    private void castSpell() {
        if (inputManager.xpressed && !spellDissabled) {
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0,-20));
            GameObject.add(playerSpell);
            spellDissabled = true;
        }

    }

    private void move() {
        this.velocity.set(0,0);
        if (inputManager.leftpressed) this.velocity.x -= 10;
        if (inputManager.rightpressed) this.velocity.x += 10;
        if (inputManager.downpressed) this.velocity.y += 10;
        if (inputManager.uppressed) this.velocity.y -= 10;

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
