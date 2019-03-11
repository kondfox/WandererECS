package systems;

import components.Position;
import entities.Entity;
import entities.Movable;

public class MoveSystem implements GameSystem {

  @Override
  public void update(Entity e) {
    if (!(e instanceof Movable)) return;
    Position positionModifier = ((Movable) e).getPositionModifier();
    if (positionModifier == null) return;
    ((Movable) e).modifyPosition(positionModifier);
  }

}
