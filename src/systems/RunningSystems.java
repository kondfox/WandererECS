package systems;

import java.util.ArrayList;
import java.util.List;

public class RunningSystems {

  private static final List<GameSystem> all = new ArrayList<>();

  public static List<GameSystem> all() {
    return all;
  }

  public static void add(GameSystem system) {
    all.add(system);
  }

  public static <S extends GameSystem> S getSystem(Class targetSystemClass) {
    for (GameSystem system : all) {
      if (targetSystemClass.isInstance(system)) {
        return (S) system;
      }
    }
    return null;
  }

}
