package entities.enemies;

import components.Boundary;
import components.Texture;
import map.Walkable;

public class Boss extends Enemy {

  public Boss() {
    super(new Texture("boss"), new Boundary().add(Walkable.class), 50);
  }
}
