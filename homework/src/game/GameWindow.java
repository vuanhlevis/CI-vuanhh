package game;

import game.base.BoxCollider;
import game.base.Contraints;
import game.base.GameObject;
import game.base.Vector2D;
import game.enemies.BlueEnemy;
import game.enemies.BossEnemy;
import game.enemies.PinkEnemy;
import game.imputs.InputManager;
import game.player.Player;
import game.screens.BackGround;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by VALV on 7/20/2017.
 */
public class GameWindow extends JFrame {
    BackGround backGround = new BackGround();
    private BufferedImage backBufferImage;
    private Graphics2D backBufferGraphics2D;
    InputManager inputManager = new InputManager();
    boolean status = true;

    public GameWindow() {

        setupWindow();
        addBackground();
        addPlayer();

        backBufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();
        setupInput();
        setVisible(true);
    }

    private void addBackground() {
        backGround = new BackGround();
        backGround.position.y = this.getHeight();
        GameObject.add(backGround);
    }

    private void addEnemies() {
        BlueEnemy enemy = new BlueEnemy();
        enemy.spawEnemy();
        enemy.coolDownspawn();
        GameObject.add(enemy);

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

    private void addPlayer() {

        Player player = new Player();
        player.setContraints(new Contraints(20, this.getHeight(), 0, backGround.renderer.getWidth()));
        player.position.set(backGround.renderer.getWidth() / 2, this.getHeight() - 50);
        player.setInputManager(inputManager);
        GameObject.add(player);

    }

    long lasUpdateTime;

    public void loop() {
        while (true) {
            long currenTime = System.currentTimeMillis();
            if (currenTime - lasUpdateTime > 17) {
                lasUpdateTime = currenTime;
                render();
                run();

            }
        }
    }

    private void run() {
        if (Math.abs(backGround.position.y) < 1111 && Math.abs(backGround.position.y) % 16 == 0) addEnemies();
        if (Math.abs(backGround.position.y) > 1115 && Math.abs(backGround.position.y) < 2300 && Math.abs(backGround.position.y) % 111 == 0)
            addPinkEnemies();
        if (Math.abs(backGround.position.y) > 2500 && status) {
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
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
//        backBufferGraphics2D.drawImage(background, 0, backgroundY, null);
        GameObject.renderAll(backBufferGraphics2D);

        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(backBufferImage, 0, 0, null);
    }


    private void setupWindow() {
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("Toughou - remade by Vũ Cơ");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                super.windowClosing(e);
            }
        });
    }
}
