package model;

public class Shape {

  private final String name;
  private double shapeX;
  private double shapeY;
  private int[][] cell;

  /**
   * . get the specific shape.
   *
   * @param name the name of the shape
   * @param shapeX the middle coordinates x
   * @param shapeY the middle coordinates y
   * @param cell the cell in this shape
   */
  public Shape(String name, double shapeX, double shapeY, int[][] cell) {
    this.name = name;
    this.cell = cell.clone();
    this.shapeX = shapeX;
    this.shapeY = shapeY;

  }

  public int[][] getCell() {
    int[][] a = cell;
    return a;
  }

  public double getShapeX() {
    return shapeX;
  }

  public double getShapeY() {
    return shapeY;
  }

  public String getName() {
    return name;
  }
}
