package utils.managers;

import java.util.concurrent.atomic.AtomicInteger;

public class EntityManager extends ResourceManager {

  private static AtomicInteger id = new AtomicInteger();

  public static int getId() {
    return id.getAndIncrement();
  }

}
