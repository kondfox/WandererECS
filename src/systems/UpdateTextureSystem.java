package systems;

import components.Position;
import entities.Entity;
import entities.HasTextureMap;

import java.util.List;
import java.util.stream.Collectors;

public class UpdateTextureSystem implements GameSystem {

  @Override
  public void update(Entity e) {
    if (!(e instanceof HasTextureMap)) return;
    HasTextureMap entity = (HasTextureMap) e;
    List<Position> positionModifiers = e.getUpdates().stream()
            .filter(c -> c instanceof Position)
            .map(p -> (Position) p)
            .collect(Collectors.toList());
    if (positionModifiers == null || positionModifiers.size() == 0) return;
    Position positionModifier = positionModifiers.get(0);
    entity.updateTexture(positionModifier);
  }
}
