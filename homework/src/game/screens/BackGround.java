package game.screens;

import game.Utils;
import game.base.GameObject;
import game.base.ImageRenderer;
import game.base.Vector2D;

/**
 * Created by VALV on 7/25/2017.
 */
public class BackGround extends GameObject {
    public BackGround () {
        super();
        this.renderer = new ImageRenderer(Utils.loadAssetImage("background/0.png"));
        this.renderer.anchor.set(0,1);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (this.position.y - this.renderer.getHeight() < 0)
            this.position.addUp(0,1);
    }
}
