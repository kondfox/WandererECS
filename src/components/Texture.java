package components;

import utils.managers.TextureManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Texture {

  public static final int TILE_SIZE = 72;
  private static final Map<String, TextureManager> texMap = new HashMap<>();

  private TextureManager manager;
  private String fileName;

  public Texture(String fileName) {
    this.fileName = fileName;
    TextureManager oldTexture = texMap.get(fileName);
    if (oldTexture != null) {
      manager = oldTexture;
      manager.addReference();
    } else {
      try {
        System.out.printf("Loading texture: %s\n", fileName);
        if (fileName == "wizard") {
          manager = new TextureManager(ImageIO.read(new File("assets/" + fileName + ".gif")));
          texMap.put(fileName, manager);
        } else {
          manager = new TextureManager(ImageIO.read(new File("assets/" + fileName + ".png")));
          texMap.put(fileName, manager);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void render(Graphics g, int x, int y) {
    g.drawImage(manager.getImage(), x * TILE_SIZE, y * TILE_SIZE, null);
  }

  @Override
  protected void finalize() throws Throwable {
    if (manager.removeReference() && !fileName.isEmpty()) {
      texMap.remove(fileName);
      System.out.printf("Removing texture: %s\n", fileName);
    }
    super.finalize();
  }
}
