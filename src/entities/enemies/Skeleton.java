package entities.enemies;

import components.Boundary;
import components.Texture;
import map.Walkable;

public class Skeleton extends Enemy {

  public Skeleton() {
    super(new Texture("skeleton"), new Boundary().add(Walkable.class), 1);
  }
}
