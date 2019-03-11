package entities;

import components.Component;
import components.Position;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Movable extends Positioned, Entity {

  default void modifyPosition(Position p) {
    this.getPosition().modifyPosition(p);
    clearPositionModifiers();
  }

  default Position getPositionModifier() {
    Optional<Position> positionModifier = this.getUpdates().stream()
            .filter(c -> c instanceof Position)
            .map(c -> (Position) c)
            .reduce((modifiedPosition, position) -> modifiedPosition.modifyPosition(position));
    if (positionModifier.isPresent()) {
      return positionModifier.get();
    }
    return null;
  }

  default void clearPositionModifiers() {
    List<Component> updates = this.getUpdates();
    List<Position> positionModifiers = updates.stream()
            .filter(c -> c instanceof Position)
            .map(c -> (Position) c)
            .collect(Collectors.toList());
    updates.removeAll(positionModifiers);
  }

}
