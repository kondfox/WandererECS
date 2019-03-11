package components;

import java.util.ArrayList;
import java.util.List;

public class Boundary {

  private List<Class> boundaries;

  public Boundary() {
    boundaries = new ArrayList<>();
  }

  public Boundary add(Class c) {
    boundaries.add(c);
    return this;
  }

  public List<Class> getBoundaries() {
    return boundaries;
  }
}
