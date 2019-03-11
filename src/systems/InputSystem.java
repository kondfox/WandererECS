package systems;

import components.Position;
import entities.Entity;
import entities.KeyboardHandled;
import entities.Pool;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputSystem {

  private final Map<Integer, Position> positionModifiers = new HashMap<Integer, Position>() {{
    put(KeyEvent.VK_UP, new Position(0, -1));
    put(KeyEvent.VK_W, new Position(0, -1));
    put(KeyEvent.VK_DOWN, new Position(0, 1));
    put(KeyEvent.VK_S, new Position(0, 1));
    put(KeyEvent.VK_LEFT, new Position(-1, 0));
    put(KeyEvent.VK_A, new Position(-1, 0));
    put(KeyEvent.VK_RIGHT, new Position(1, 0));
    put(KeyEvent.VK_D, new Position(1, 0));
  }};

  public InputSystem() {
  }

  public void keyReleased(KeyEvent keyEvent) {
   List<Entity> keyBoardHandledEntities = getKeyBoardHandledEntities();
    Position positionModifier = positionModifiers.get(keyEvent.getKeyCode());
    if (positionModifier == null) return;

    keyBoardHandledEntities.stream().forEach(e -> e.update(positionModifier));
  }

  public List<Entity> getKeyBoardHandledEntities() {
    return Pool.all().stream()
            .filter(e -> e instanceof KeyboardHandled)
            .collect(Collectors.toList());
  }
}
