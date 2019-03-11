package entities.enemies;

import components.Boundary;
import components.Component;
import components.Position;
import components.Texture;
import entities.*;
import utils.managers.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Enemy implements Entity, Positioned, Movable, Drawable, HasBoundary, AutoStepping {

  private int id;
  private Position position;
  private List<Component> updates;
  private Texture texture;
  private Boundary boundary;
  private int speed;
  private AtomicInteger stepTryCount;

  public Enemy(Texture t, Boundary b, int speed) {
    id = EntityManager.getId();
    updates = new ArrayList<>();
    texture = t;
    position = new Position(0, 0);
    boundary = b;
    this.speed = speed;
    stepTryCount = new AtomicInteger(0);
    Pool.add(this);
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public Boundary getBoundary() {
    return boundary;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public List<Component> getUpdates() {
    return updates;
  }

  @Override
  public Texture getTexture() {
    return texture;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public AtomicInteger getStepTryCount() {
    return stepTryCount;
  }
}
