package entities;

import components.Boundary;
import components.Component;
import components.Position;
import components.Texture;
import map.Walkable;
import utils.managers.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class Wizard implements Entity, Positioned, Drawable, Movable, KeyboardHandled {

  private int id;
  private List<Component> updates;
  private Texture texture;
  private Position position;
  private Boundary boundary;

  public Wizard() {
    this.id = EntityManager.getId();
    this.updates = new ArrayList<>();
    this.texture = new Texture("wizard");
    this.position = new Position(0, 0);
    this.boundary = new Boundary().add(Walkable.class);
    Pool.add(this);
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public List<Component> getUpdates() {
    return updates;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public void modifyPosition(Position p) {
    this.position.modifyPosition(p);
  }

  @Override
  public Texture getTexture() {
    return this.texture;
  }

  //@Override
  public Boundary getBoundary() {
    return this.boundary;
  }
}
