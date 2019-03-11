package entities;

import components.Position;

public interface HasTextureMap extends Drawable {

  void updateTexture(Position p);

}
