package game.player;

import game.Utils;
import game.bases.FrameCounter;
import game.bases.GameObject;
import game.bases.Vector2D;
import game.bases.renderers.ImageRenderer;
import tklibs.AudioUtils;


/**
 * Created by levua on 7/29/2017.
 */
public class PlayerExplove extends GameObject {
    ImageRenderer imageRenderer1;
    ImageRenderer imageRenderer2;
    ImageRenderer imageRenderer3;
    ImageRenderer imageRenderer4;
    ImageRenderer imageRenderer5;
    ImageRenderer imageRenderer6;
    ImageRenderer imageRenderer7;
    FrameCounter frameCounter;
    AudioUtils audioUtils = new AudioUtils();

    public PlayerExplove() {

        super();
        imageRenderer1 = new ImageRenderer(Utils.loadAssetImage("players/explosions/0.png"));
        imageRenderer2 = new ImageRenderer(Utils.loadAssetImage("players/explosions/1.png"));
        imageRenderer3 = new ImageRenderer(Utils.loadAssetImage("players/explosions/2.png"));
        imageRenderer4 = new ImageRenderer(Utils.loadAssetImage("players/explosions/3.png"));
        imageRenderer5 = new ImageRenderer(Utils.loadAssetImage("players/explosions/4.png"));
        imageRenderer6 = new ImageRenderer(Utils.loadAssetImage("players/explosions/5.png"));
        imageRenderer7 = new ImageRenderer(Utils.loadAssetImage("players/explosions/6.png"));
        frameCounter = new FrameCounter(5);
        this.renderer = imageRenderer1;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter.run()) {
            AudioUtils.playMedia("assets/music/sfx/player-dead.wav");
            if (this.renderer == imageRenderer1) this.renderer = imageRenderer2;
            else if (this.renderer == imageRenderer2) this.renderer = imageRenderer3;
            else if (this.renderer == imageRenderer3) this.renderer = imageRenderer4;
            else if (this.renderer == imageRenderer4) this.renderer = imageRenderer5;
            else if (this.renderer == imageRenderer5) this.renderer = imageRenderer6;
            else if (this.renderer == imageRenderer6) this.renderer = imageRenderer7;
            if (this.renderer == imageRenderer7) {
                this.isActive = false;
                this.renderer = imageRenderer1;
            }
            frameCounter.reset();
        }
    }
}
