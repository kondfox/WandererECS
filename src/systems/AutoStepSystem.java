package systems;

import components.Boundary;
import components.Position;
import entities.*;

public class AutoStepSystem implements GameSystem {

  @Override
  public void update(Entity e) {
    if (!(e instanceof AutoStepping)) return;
    if (!((AutoStepping) e).shouldStep()) return;
    Area area = Pool.getArea();
    Boundary boundary = e instanceof HasBoundary ? ((HasBoundary) e).getBoundary() : null;
    Position randomPosition = area.getRandomDirection(((Positioned) e).getPosition(), boundary);
    e.update(((Positioned) e).getPosition().getPositionDiff(randomPosition));
  }
}
