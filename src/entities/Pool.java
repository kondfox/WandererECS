package entities;

import java.util.ArrayList;
import java.util.List;

public class Pool {

  private static final List<Entity> all = new ArrayList<>();
  private static Area area;
  private static Hero hero;

  public static void add(Entity entity) {
    all.add(entity);
    if (entity instanceof Area) {
      area = (Area) entity;
    }
    if (entity instanceof Hero) {
      hero = (Hero) entity;
    }
  }

  public static List<Entity> all() {
    return all;
  }

  public static Area getArea() {
    return area;
  }

  public static Hero getHero() {
    return hero;
  }

}
