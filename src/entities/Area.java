package entities;

import components.Boundary;
import components.Component;
import components.Position;
import components.Texture;
import map.Floor;
import map.Tile;
import map.Wall;
import utils.managers.EntityManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Area implements Entity, Drawable {

  private int id;
  private List<Component> updates;
  private Tile[][] map;
  private int width;
  private int height;
  private String[][][] levels;
  private static int noriCounter = 0;

  private String[][] levelN = {
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "W", "F", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "W", "W", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "W", "F", "W", "F", "W", "F", "F", "F", "F" },
          { "F", "W", "F", "F", "W", "W", "F", "F", "F", "F" },
          { "F", "W", "F", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" }
  };

  private String[][] levelO = {
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "W", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "F", "W", "F", "F", "F", "F" },
          { "F", "F", "F", "W", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" }
  };

  private String[][] levelR = {
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "W", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "W", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "W", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "W", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" }
  };

  private String[][] levelI = {
          { "F", "F", "F", "W", "W", "W", "F", "F", "F", "F" },
          { "F", "F", "F", "W", "W", "W", "F", "F", "F", "F" },
          { "F", "F", "F", "W", "W", "W", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "F", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "W", "F", "F", "F", "F", "F" },
          { "F", "F", "F", "F", "W", "F", "F", "F", "F", "F" }
  };

  public Area(int width, int height) {
    this.id = EntityManager.getId();
    this.updates = new ArrayList<>();
    this.width = width;
    this.height = height;
    levels = new String[][][]{levelN, levelO, levelR, levelI};
    initMap(width, height);
    Pool.add(this);
  }

  private void initMap(int width, int height) {
    map = new Tile[height][width];
    String[][] randomLevel = levels[noriCounter++ % levels.length];
    for (int i = 0; i < randomLevel.length; i++) {
      String[] tileStringRow = randomLevel[i];
      for (int j = 0; j < tileStringRow.length; j++) {
        Position p = new Position(j, i);
        map[i][j] = tileStringRow[j] == "F" ? new Floor(p) : new Wall(p);
      }
    }
  }

  private String[][] randomLevel() {
    return levels[(int) (Math.random() * levels.length)];
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public List<Component> getUpdates() {
    return updates;
  }

  @Override
  public Texture getTexture() {
    return null;
  }

  @Override
  public void render(Graphics g) {
    Arrays.stream(this.map).forEach(r -> Arrays.stream(r).forEach(t -> ((Drawable) t).render(g)));
  }

  public Tile getTile(Position p) {
    return map[p.y()][p.x()];
  }

  public Position getRandomPosition(Boundary boundary) {
    return getRandomPosition(new Position(width / 2, height / 2), Math.min(width / 2, height / 2), boundary);
  }

  public Position getRandomDirection(Position startPosition, Boundary boundary) {
    List<Tile> nearbyTiles = getNearbyTiles(startPosition, 1, boundary);
    List<Position> notDiagonalPositions = nearbyTiles.stream()
            .map(t -> t.getPosition())
            .filter(p -> !isDiagonal(p.getPositionDiff(startPosition)))
            .collect(Collectors.toList());
    if (notDiagonalPositions.size() == 0) return new Position(0, 0);
    return notDiagonalPositions.get((int) (Math.random() * notDiagonalPositions.size()));
  }

  public Position getRandomPosition(Position startPosition, int maxDistance, Boundary boundary) {
    Random random = new Random();
    List<Tile> validTiles = getNearbyTiles(startPosition, maxDistance, boundary);
    if (validTiles.size() == 0) return startPosition;
    return validTiles.get(random.nextInt(validTiles.size())).getPosition();
  }

  private List<Tile> getNearbyTiles(Position position, int distance, Boundary boundary) {
    List<Tile> nearbyTiles = new ArrayList<>();

    for (int yModifier = -distance; yModifier <= distance ; yModifier++) {
      for (int xModifier = -distance; xModifier <= distance; xModifier++) {
        if (yModifier == 0 && xModifier == 0) continue;
        Position neighbourPosition = new Position(position.x() + xModifier, position.y() + yModifier);
        if (isValidLocation(neighbourPosition, boundary)) {
          nearbyTiles.add(getTile(neighbourPosition));
        }
      }
    }

    return nearbyTiles;
  }

  private boolean isDiagonal(Position positionModifier) {
    return Math.abs(positionModifier.x()) + Math.abs(positionModifier.y()) > 1;
  }

  public boolean isValidLocation(Position position, Boundary boundary) {
    if (position == null) return false;
    if (isOutOfMap(position)) {
      return false;
    }
    if (boundary == null) return true;
    return unsatisfies(position, boundary);
  }

  private boolean unsatisfies(Position position, Boundary boundary) {
    for (Class c : boundary.getBoundaries()) {
      if (!c.isInstance(getTile(position))) {
        return false;
      }
    }
    return true;
  }

  private boolean isOutOfMap(Position position) {
    return position.x() < 0 || position.x() >= width || position.y() < 0 || position.y() >= height;
  }

}
