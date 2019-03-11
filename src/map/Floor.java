package map;

import components.Position;

public class Floor extends Tile implements Walkable {

  public Floor(Position position) {
    super("floor", position);
  }

}
