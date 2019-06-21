import java.io.BufferedReader;
import java.io.InputStreamReader;
import model.Game;
import model.Grid;
import model.ShapeCollection;


public final class Shell {

  private Shell() {
  }

  /**
   * . compute the game with user.
   *
   * @param args array
   * @throws Exception the fault of the programm
   */
  public static void main(String[] args) throws Exception {
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    execute(stdin);

  }


  private static void execute(BufferedReader stdin) throws Exception {
    boolean quit = false;
    int zahl = 0;
    Grid t = new Game();
    String[] b = null;
    while (!quit) {
      System.out.print("gol> ");
      String input = null;
      //String input = stdin.readLine();
      try {
        input = stdin.readLine();
      } catch (NullPointerException e) {
        // throw exception
      }
      if (input != null) {
        b = input.trim().split("\\s+");
      } else {
        quit = true;
      }

      String[] tokens = b;

      if (tokens[0].equals("QUIT") || tokens[0].equals("quit")) {
        quit = true;
      } else if (tokens[0].equals("HELP")) {
        System.out.println("NEW x y:beginn a new game");
        System.out.println("ALIVE i j:set the position i j live");
        System.out.println("DEAD i j:set the position i j die");
        System.out.println("GENERATE:compute the next generation in the game");
        System.out.println("PRINT:output the game");
        System.out.println("CLEAR:set all the live cell die");
        System.out.println("RESIZE x y:resize the game");
        System.out.println("SHAPE s:output the shape");
        System.out.println("QUIT :end the game");
      } else if (tokens[0].equals("NEW") || tokens[0].equals("new")) {
        if (Integer.parseInt(tokens[1]) < 0 || Integer.parseInt(tokens[2]) < 0) {
          System.out.println("Error! Diese Nummer ist negativ.");
        } else {
          Grid a = new Game(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
          t = a;
          zahl = zahl + 1;
        }
      } else if (tokens[0].equals("ALIVE") || tokens[0].equals("alive")) {
        if (tokens.length != 3) {
          System.out.println("Error! Diese Eingabe ist zu kurz.");
        } else if (t.getColumns() == 0 || t.getRows() == 0 || Integer.parseInt(tokens[1]) < 0
            || Integer.parseInt(tokens[2]) < 0 || Integer.parseInt(tokens[1]) > t.getColumns() - 1
            || Integer.parseInt(tokens[2]) > t.getRows() - 1) {
          System.out.println("Error! Noch kein Spielfeld vorhanden.");
        } else {
          t.setAlive(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), true);
        }
      } else if (tokens[0].equals("DEAD") || tokens[0].equals("dead")) {
        t.setAlive(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), false);
      } else if (tokens[0].equals("GENERATE") || tokens[0].equals("generate")) {
        t.next();
        System.out.println("Generation:" + " " + t.getGenerations());
      } else if (tokens[0].equals("PRINT") || tokens[0].equals("print")) {
        if (zahl == 0) {
          System.out.println("Error! Noch kein Spielfeld vorhanden.");
        } else {
          System.out.println(t.toString());
        }
      } else if (tokens[0].equals("CLEAR") || tokens[0].equals("clear")) {
        t.clear();
      } else if (tokens[0].equals("RESIZE") || tokens[0].equals("resize")) {
        t.resize(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
      } else if (tokens[0].equals("SHAPE") || tokens[0].equals("shape")) {
        t.clear();
        ((Game) t).shapeCompute(ShapeCollection.getShapeByName(tokens[1]));

      }

    }
  }
}