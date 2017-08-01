package game;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import game.bases.Contraints;
import game.bases.GameObject;
import game.bases.GameObjectPool;
import game.bases.physics.Physics;
import game.enemies.BlueEnemy;
import game.enemies.BossEnemy;

import game.enemies.PinkEnemy;
import game.inputs.InputManager;
import game.player.Player;
import game.player.PlayerSpell;
import game.screnes.BackGround;
import game.screnes.Settings;
import javafx.scene.media.MediaPlayer;
import tklibs.AudioUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static sun.misc.PostVMInitHook.run;

/**
 * Created by VALV on 7/9/2017.
 */
public class GameWindow extends JFrame {

    BackGround backGroud = new BackGround();
    boolean status = true;
    private MediaPlayer mediaPlayer;
    private BufferedImage backBufferImage;

    private Graphics2D backBufferGraphic2D;

    InputManager inputManager = new InputManager();

    public GameWindow() {

        setUpWindow();
        addBackground();
        addPlayer();
        addAudio();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
        this.setVisible(true);
    }

    private void addAudio() {
        AudioUtils.initialize();
        mediaPlayer = AudioUtils.playMedia("assets/music/1.mp3");
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnRepeat(this::loop);
        mediaPlayer.setOnPlaying(this::run);

    }

    private void addBackground() {
        backGroud = new BackGround();
        backGroud.screenPosition.y = this.getHeight();
        GameObject.add(backGroud);
    }


    private void addPlayer() {
        Player player = new Player();
        player.setContraints(new Contraints(20, this.getHeight(), 0, backGroud.getWidth()));
        player.position.set(backGroud.getWidth() / 2, this.getHeight() - 50);
        player.setInputManager(inputManager);

        GameObject.add(player);
    }

    private void setupInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyRelease(e);
            }
        });

    }

    long lastUpdateTime;

    public void loop() {


        while (true) {


            long currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdateTime > 17) {
                lastUpdateTime = currentTime;
                render();
                run();
            }

//            if (inputManager.enterPress) {
//                GameObject.clearAll();
//                GameObjectPool.clearAll();
//                Physics.clearAll();
//            }
        }


    }


    public void Init() {
        BossEnemy.instance = null;
        Player.instance = null;
        addBackground();
        addPlayer();


        this.setVisible(true);

        setUpWindow();
        addBackground();
        addPlayer();


        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic2D = (Graphics2D) backBufferImage.getGraphics();

        setupInput();
    }

    private void addEnemies() {
        BlueEnemy enemy = new BlueEnemy();
        enemy.spawEnemy();
        enemy.coolDownspawn();
        GameObject.add(enemy);

    }


    private void run() {
        if (Math.abs(backGroud.screenPosition.y) < 1111 && Math.abs(backGroud.screenPosition.y) % 16 == 0) addEnemies();
        if (Math.abs(backGroud.screenPosition.y) > 1115 && Math.abs(backGroud.screenPosition.y) < 2300 && Math.abs(backGroud.screenPosition.y) % 111 == 0)
            addPinkEnemies();
        if (Math.abs(backGroud.screenPosition.y) > 2500 && status) {
            addBossEnemy();
            status = false;
        }

        GameObject.runAll();
        GameObject.changeAllPicture();
    }

    private void addBossEnemy() {
        BossEnemy enemyBoss = new BossEnemy();
        enemyBoss.spawnEnemyBoss();
        GameObject.add(enemyBoss);
    }

    private void addPinkEnemies() {
        PinkEnemy enemy = new PinkEnemy();
        enemy.spawnEnemy();
        PinkEnemy enemy1 = new PinkEnemy();
        enemy1.spawnEnemy2();

        GameObject.add(enemy);
        GameObject.add(enemy1);
    }

    private void render() {
        backBufferGraphic2D.setColor(Color.BLACK);
        backBufferGraphic2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        GameObject.renderAll(backBufferGraphic2D);
        backBufferGraphic2D.setColor(Color.GREEN);

        if (backGroud.screenPosition.y > 2600) {
            backBufferGraphic2D.drawString("Boss", 400, 50);
            backBufferGraphic2D.drawString("BOSS HP:       " + BossEnemy.instance.HP, 400, 100);
            backBufferGraphic2D.drawString("PLAYER HP:     " + Player.instance.HP, 400, 150);
            if (BossEnemy.instance.HP <= 0) {
                backBufferGraphic2D.drawString("----- YOU WIN -----", Settings.gameplaywidth / 2, Settings.gameplayheight / 2);
                backBufferGraphic2D.setColor(Color.CYAN);
                backBufferGraphic2D.drawString("Press Enter to exit",Settings.gameplaywidth / 2, Settings.gameplayheight / 2 - 50);
                this.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                System.exit(0);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

            }

        } else {
            backBufferGraphic2D.drawString("HP :        " + Player.instance.HP, 400, 100);
        }

        if (Player.instance.HP <= 0) {
            backBufferGraphic2D.setColor(Color.RED);
            backBufferGraphic2D.drawString("----- YOU LOSE -----", Settings.gameplaywidth / 2 - 50, Settings.gameplayheight / 2);
            backBufferGraphic2D.setColor(Color.CYAN);
            backBufferGraphic2D.drawString("Press Enter to exit",Settings.gameplaywidth / 2 - 50, Settings.gameplayheight / 2 - 50);
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_ENTER:
                            System.exit(0);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }


        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void setUpWindow() {
        this.setSize(800, 600);

        this.setResizable(false);
        this.setTitle("Touhou - remade by Vũ Cơ");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}