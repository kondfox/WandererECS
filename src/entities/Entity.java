package entities;

import components.Component;

import java.util.List;

public interface Entity {

  int getId();
  List<Component> getUpdates();

  default void update(Component component) {
    this.getUpdates().add(component);
  }

}
