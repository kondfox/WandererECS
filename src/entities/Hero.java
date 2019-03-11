package entities;

import components.*;
import map.Walkable;
import utils.managers.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hero implements Entity, Positioned, Drawable, Movable, KeyboardHandled, HasBoundary, HasTextureMap {

  private int id;
  private List<Component> updates;
  private Texture texture;
  private Position position;
  private Boundary boundary;
  private TextureMap textureMap;
  private Map<Position, Texture> tm;

  public Hero() {
    this.id = EntityManager.getId();
    this.updates = new ArrayList<>();
    this.texture = new Texture("hero-down");
    this.position = new Position(0, 0);
    this.boundary = new Boundary().add(Walkable.class);
    this.textureMap = new TextureMap()
            .add(new Position(0, -1), new Texture("hero-up"))
            .add(new Position(0, 1), new Texture("hero-down"))
            .add(new Position(-1, 0), new Texture("hero-left"))
            .add(new Position(1, 0), new Texture("hero-right"));
    Pool.add(this);
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
  public Texture getTexture() {
    return this.texture;
  }

  @Override
  public void updateTexture(Position position) {
    Texture updatedTexture = textureMap.getTexture(position);
    if (updatedTexture != null) {
      texture = updatedTexture;
    }
  }

  @Override
  public Boundary getBoundary() {
    return this.boundary;
  }
}
