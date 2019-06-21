package model;

public class Cell {


  private int row;
  private int col;
  private int neighbornumber;
  private boolean alive;
  private static final int hashfaktor = 5000;

  public Cell() {

  }

  /**
   * . construt a Cell.
   *
   * @param col column
   * @param row rows
   */
  public Cell(int col, int row) {
    this.row = row;
    this.col = col;
    //this.cell=new int[row][col];
    this.neighbornumber = 0;
    this.alive = false;
  }

  public void createAlive() {
    alive = true;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public void setneighbornumber(int neighbornumber) {
    this.neighbornumber = neighbornumber;
  }

  public int getneighbornumber() {
    return neighbornumber;
  }

  public boolean getAlive() {
    return alive;
  }

  public void createDead() {
    alive = false;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  /**
   * . get the next condition of the cell.
   *
   * @param nowAlive now Situation
   * @param neighbornumber the number of the neighber
   * @return ture, if next generation still live
   */
  public boolean getNextCellCondition(boolean nowAlive, int neighbornumber) {
    if (nowAlive == true && (neighbornumber == 2 || neighbornumber == 3) || (nowAlive == false
        && neighbornumber == 3)) {
      return true;
    }
    return false;
  }

  /**
   * . compare two Object.
   *
   * @param o here is Cell
   * @return true, if two Obiect is same
   */
  public boolean equals(Object o) {
    if (!(o instanceof Cell)) {
      return false;
    }
    return col == ((Cell) o).col && row == ((Cell) o).row;
  }

  public int hashCode() {
    return hashfaktor * row + col;
  }

}