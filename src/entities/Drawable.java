package entities;

import components.Position;
import components.Texture;

import java.awt.*;

public interface Drawable {

  Texture getTexture();

  default void render(Graphics g, int x, int y) {
    this.getTexture().render(g, x, y);
  }

  default void render(Graphics g) {
    if (this instanceof Positioned) {
      Position p = ((Positioned) this).getPosition();
      this.getTexture().render(g, p.x(), p.y());
    }
  }

}
