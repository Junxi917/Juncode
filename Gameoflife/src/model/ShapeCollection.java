package model;

public class ShapeCollection {

  private static final Shape Blinker = new Shape("Blinker", 1, 0,
      new int[][]{{0, 0}, {1, 0}, {2, 0}});
  private static final Shape Boat = new Shape("Boat", 1, 1,
      new int[][]{{0, 0}, {1, 0}, {0, 1}, {2, 1}, {1, 2}});
  private static final Shape Spaceship = new Shape("Spaceship", 2, 1,
      new int[][]{{1, 0}, {4, 0}, {0, 1}, {0, 2}, {4, 2}, {0, 3}, {1, 3}, {2, 3}, {3, 3}});
  private static final Shape Block = new Shape("Block", 0.5, 0.5,
      new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});
  private static final Shape Toad = new Shape("Toad", 1.5, 0.5,
      new int[][]{{1, 0}, {2, 0}, {3, 0}, {0, 1}, {0, 2}, {0, 3}});
  private static final Shape Glider = new Shape("Glider", 1, 1,
      new int[][]{{0, 0}, {1, 0}, {2, 0}, {0, 1}, {1, 2}});
  private static final Shape Pulsar = new Shape("Pulsar", 6, 6,
      new int[][]{{2, 0}, {3, 0}, {9, 0}, {10, 0}, {3, 1}, {4, 1}, {8, 1}, {9, 1}, {0, 2}, {3, 2},
          {5, 2}, {7, 2}, {9, 2}, {12, 2}, {0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 3}, {7, 3}, {8, 3},
          {10, 3}, {11, 3}, {12, 3}, {1, 4}, {3, 4}, {5, 4}, {7, 4}, {9, 4}, {11, 4}, {2, 5},
          {3, 5}, {4, 5}, {8, 5}, {9, 5}, {10, 5}, {2, 7}, {3, 7}, {4, 7}, {8, 7}, {9, 7}, {10, 7},
          {1, 8}, {3, 8}, {5, 8}, {7, 8}, {9, 8}, {11, 8}, {0, 9}, {1, 9}, {2, 9}, {4, 9}, {5, 9},
          {7, 9}, {8, 9}, {10, 9}, {11, 9}, {12, 9}, {0, 10}, {3, 10}, {5, 10}, {7, 10}, {9, 10},
          {12, 10}, {3, 11}, {4, 11}, {8, 11}, {9, 11}, {2, 12}, {3, 12}, {9, 12}, {10, 12}});
  private static final Shape[] COLLECTION = new Shape[]{Blinker, Pulsar, Glider, Block, Toad, Boat,
      Spaceship};

  public static Shape[] getShapes() {
    Shape[] t = COLLECTION;
    return t;
  }

  /**
   * . get the special shape.
   *
   * @param name the name of the shape
   * @return the shape with the name
   */
  public static Shape getShapeByName(String name) {
    Shape[] shapes = getShapes();
    for (int i = 0; i < shapes.length; i++) {
      if (shapes[i].getName().equals(name)) {
        return shapes[i];
      }
    }
    return null;
  }

}