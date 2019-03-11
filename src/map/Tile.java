package map;

import components.Position;
import components.Texture;
import entities.Drawable;
import entities.Positioned;

public abstract class Tile implements Drawable, Positioned {

  private Texture texture;
  private Position position;

  public Tile(String tileType, Position position) {
    this.texture = new Texture(tileType);
    this.position = position;
  }

  @Override
  public Texture getTexture() {
    return texture;
  }

  @Override
  public Position getPosition() {
    return position;
  }

}
