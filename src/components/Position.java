package components;

public class Position implements Cloneable, Component {

  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int x() {
    return this.x;
  }

  public int y() {
    return this.y;
  }

  public Position modifyPosition(Position p) {
    if (p == null) return this;
    this.x += p.x;
    this.y += p.y;
    return this;
  }

  @Override
  public Position clone() {
    return new Position(this.x, this.y);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position)) return false;
    Position otherPosition = (Position) obj;
    return this.x == otherPosition.x && this.y == otherPosition.y;
  }

  public boolean isValidPosition(int xLimit, int yLimit) {
    return x >= 0 && y >= 0 && x < xLimit && y < yLimit;
  }

  public Position getPositionDiff(Position position) {
    if (position == null) return new Position(0, 0);
    return new Position(position.x - x, position.y - y);
  }

}
