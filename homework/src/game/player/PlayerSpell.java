package game.player;

import game.Utils;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

import java.util.Vector;

/**
 * Created by VALV on 7/20/2017.
 */
public class PlayerSpell extends GameObject{

    public PlayerSpell () {
        this.renderer = new ImageRenderer(Utils.loadAssetImage("player-spell/a/0.png"));
    }

    public void run() {
        this.position.addUp(0,-10);
    }
}
