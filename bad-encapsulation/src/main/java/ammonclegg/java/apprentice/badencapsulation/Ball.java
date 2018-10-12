package ammonclegg.java.apprentice.badencapsulation;

import java.util.Objects;

/**
 * @author ammonclegg on 10/12/18.
 */
public class Ball {

  public int x;
  public int y;
  public int z;
  public int radius;

  public Ball(int x, int y, int z, int radius) {
    this.setX(x);
    this.setY(y);
    this.setZ(z);
    this.setRadius(radius);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getZ() {
    return z;
  }

  public void setZ(int z) {
    this.z = z;
  }

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    if (radius > 0) {
      this.radius = radius;
    }
    else {
      throw new RuntimeException("Radius must be greater than 0");
    }
  }

  @Override
  public String toString() {
    return "Ball{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        ", radius=" + radius +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ball ball = (Ball) o;
    return x == ball.x &&
        y == ball.y &&
        z == ball.z &&
        radius == ball.radius;
  }

  @Override
  public int hashCode() {

    return Objects.hash(x, y, z, radius);
  }
}
