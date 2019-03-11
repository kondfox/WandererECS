package systems;

import components.Boundary;
import components.Position;
import entities.*;

public class CollisionSystem implements GameSystem {

  @Override
  public void update(Entity e) {
    if (!(e instanceof Movable)) return;
    Position positionModifier = ((Movable) e).getPositionModifier();
    Position targetPosition = ((Movable) e).getPosition().clone().modifyPosition(positionModifier);
    if (isCollision(e, targetPosition)) {
      ((Movable) e).clearPositionModifiers();
    }
  }

  public boolean isCollision(Entity e, Position targetPosition) {
    Area area = Pool.getArea();
    Boundary boundary = e instanceof HasBoundary ? ((HasBoundary) e).getBoundary() : null;
    return !area.isValidLocation(targetPosition, boundary);
  }

}
