package components;

import java.util.HashMap;
import java.util.Map;

public class TextureMap {

  private Map<Position, Texture> textures;

  public TextureMap() {
    textures = new HashMap<>();
  }

  public TextureMap(Map<Position, Texture> textures) {
    this.textures = textures;
  }

  public TextureMap add(Position p, Texture t) {
    textures.put(p, t);
    return this;
  }

  public Texture getTexture(Position position) {
    for (Map.Entry<Position, Texture> t : textures.entrySet()) {
      if (t.getKey().equals(position)) {
        return t.getValue();
      }
    }
    return null;
  }

}
