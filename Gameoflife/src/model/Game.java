package model;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;

public class Game implements Grid {

  Cell[][] cells;
  private int rows;
  private int column;
  private int generation;


  public Game() {

  }

  /**
   * . get the shape to the Game.
   *
   * @param a the shape of the shape collection.
   */
  public void shapeCompute(Shape a) {

    double x = (this.column - 1) * 0.5 - a.getShapeX();
    double y = (this.rows - 1) * 0.5 - a.getShapeY();
    for (int[] intArray : a.getCell()) {
      int m = intArray[0];
      int n = intArray[1];
      this.setAlive(m + (int) x, n + (int) y, true);

    }
  }

  /**
   * . get the Grid with column , rows.
   *
   * @param column the column of the Game
   * @param rows the rows of the Game
   */
  public Game(int column, int rows) {
    this.rows = rows;
    this.column = column;
    this.cells = new Cell[column][rows];
    if (rows == 0 || column == 0) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < rows; j++) {
        cells[i][j] = new Cell(i, j);
      }
    }
  }

  /**
   * . get the special Cell in the game.
   *
   * @param column get column
   * @param rows get rows
   * @return the special cell
   */
  public Cell getCellAt(int column, int rows) {
    if ((rows < 0) || (rows > this.rows - 1)) {
      throw new RuntimeException("Bad row parameter to getCellAt: " + rows);
    }

    if ((column < 0) || (column > this.column - 1)) {
      throw new RuntimeException("Bad column parameter to getCellAt: " + column);
    }

    return cells[column][rows];

  }

  @Override
  public boolean isAlive(int col, int row) {
    if ((col < 0) || (col > this.column - 1) || (row < 0) || (row > this.rows - 1)) {
      throw new IllegalArgumentException();
    }
    return cells[col][row].getAlive();
  }

  @Override
  public void setAlive(int col, int row, boolean alive) {
    cells[col][row].setAlive(alive);

  }

  @Override
  public void resize(int col, int row) {
    Cell[][] newGrid = new Cell[col][row];

    for (int i = 0; i < col; i++) {
      for (int j = 0; j < row; j++) {
        if (i < column && j < rows) {
          newGrid[i][j] = cells[i][j];
        } else {
          newGrid[i][j] = new Cell(i, j);
        }
      }

    }

    this.cells = newGrid;
    this.rows = row;
    this.column = col;

  }


  @Override
  public int getColumns() {
    return column;
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public Collection<Cell> getPopulation() {
    Collection<Cell> cellpop = new HashSet<>();
    for (int i = 0; i <= rows; i++) {
      for (int j = 0; j <= column; j++) {
        if (cells[i][j].getAlive() == true) {
          cellpop.add(cells[i][j]);
        }
      }
    }
    return cellpop;
  }

  @Override
  public void clear() {
    generation = 0;
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < rows; j++) {
        if (cells[i][j].getAlive() == true) {
          cells[i][j].setAlive(false);
        }
      }
    }
  }

  @Override
  public void next() {
    Cell[][] newGrid = new Cell[column][rows];
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < rows; j++) {
        newGrid[i][j] = new Cell(i, j);
      }
    }
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < rows; j++) {
        int aliveNumber = this.getaliveneighbor(i, j);
        cells[i][j].setneighbornumber(aliveNumber);
      }
    }
    for (int i = 0; i < column; i++) {
      for (int j = 0; j < rows; j++) {
        boolean aliveCondition = cells[i][j]
            .getNextCellCondition(cells[i][j].getAlive(), cells[i][j].getneighbornumber());
        newGrid[i][j].setAlive(aliveCondition);
      }
    }
    this.cells = newGrid;
    this.generation = this.generation + 1;
  }

  /**
   * . compute the number of the neighbar.
   *
   * @param x column
   * @param y row
   * @return the number of the neighbar
   */
  public int getaliveneighbor(int x, int y) {
    int pop = 0;
    for (int i = x - 1, k = x + 1; i <= k; i++) {
      for (int j = y - 1, m = y + 1; j <= m; j++) {
        if ((i >= 0 && i < column) && (j >= 0 && j < rows) && (i != x || j != y)
            && cells[i][j].getAlive() == true) {
          pop += 1;
        }
      }
    }
    return pop;
  }

  @Override
  public int getGenerations() {
    return this.generation;
  }

  /**
   * . output the Game.
   *
   * @return the whole Game
   */
  public String toString() {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < column; j++) {
        if (cells[j][i].getAlive() == true) {
          buf.append("X");

        } else {
          buf.append(".");
        }
      }
      buf.append("\n");
    }
    String s = buf.toString();
    return s;

  }


  public void changeCellStatus(int col, int row) {
    cells[col][row].setAlive(!cells[col][row].getAlive());
  }


}