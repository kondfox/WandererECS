import components.Position;
import entities.Area;
import entities.Hero;
import entities.Pool;
import entities.Wizard;
import entities.enemies.Boss;
import entities.enemies.Enemy;
import entities.enemies.Skeleton;
import systems.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Wanderer extends Canvas implements Runnable, KeyListener {

  public static final String TITLE = "Wanderer";
  public static final int WIDTH = 720;
  public static final int HEIGHT = WIDTH + 23;

  private boolean running;
  private InputSystem inputSystem;
  private Area area;
  private Hero hero;
  private List<Enemy> enemies;

  public Wanderer() {
    initSystems();

    area = new Area(10, 10);
    hero = new Hero();
    enemies = createEnemies(3);
  }

  private void initSystems() {
    inputSystem = new InputSystem();
    RunningSystems.add(new CollisionSystem());
    RunningSystems.add(new UpdateTextureSystem());
    RunningSystems.add(new AutoStepSystem());
    RunningSystems.add(new MoveSystem());
  }

  private List<Enemy> createEnemies(int enemyCount) {
    List<Enemy> enemies = new ArrayList<>();
    if (enemyCount > 0) {
      Boss boss = new Boss();
      boss.modifyPosition(area.getRandomPosition(boss.getBoundary()));
      enemies.add(boss);
    }
    for (int i = 0; i < enemyCount - 1; i++) {
      Skeleton skeleton = new Skeleton();
      skeleton.modifyPosition(area.getRandomPosition(skeleton.getBoundary()));
      enemies.add(skeleton);
    }
    return enemies;
  }

  public void start() {
    if (running) return;
    running = true;
    new Thread(this, "WandererMain-Thread").start();
  }

  public void stop() {
    if (!running) return;
    running = false;
  }

  @Override
  public void run() {
    double targetFPS = 60.;
    double nsPerTick = 1000000000. / targetFPS;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double unprocessed = 0.;
    int fps = 0;
    int tps = 0;
    boolean canRender = false;

    while (running) {
      long now = System.nanoTime();
      unprocessed += (now - lastTime) / nsPerTick;
      lastTime = now;

      if (unprocessed >= 1.) {
        tick();
        unprocessed--;
        tps++;
        canRender = true;
      } else {
        canRender = false;
      }

      if (canRender) {
        render();
        fps++;
      }

      if (System.currentTimeMillis() - 500 > timer) {
        timer += 500;
        System.out.printf("FPS: %d | TPS: %d\n", fps, tps);
        fps = 0;
        tps = 0;
        area = new Area(10, 10);
      }
    }

    System.exit(0);
  }

  private void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();

    area.render(g);
    enemies.stream().forEach(e -> e.render(g));
    hero.render(g);

    g.dispose();
    bs.show();
  }

  private void tick() {
    Pool.all().stream().forEach(e -> {
      RunningSystems.all().stream().forEach(s -> s.update(e));
    });
  }

  public static void main(String[] args) {
    Wanderer game = new Wanderer();
    JFrame frame = new JFrame(TITLE);
    frame.add(game);
    frame.setSize(new Dimension(WIDTH, HEIGHT));
    frame.setResizable(false);
    frame.setFocusable(true);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        System.err.println("Exiting game");
        game.stop();
      }
    });
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.requestFocus();
    frame.addKeyListener(game);
    game.start();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    inputSystem.keyReleased(e);
  }
}