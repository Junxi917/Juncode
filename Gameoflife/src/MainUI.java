import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import model.Game;
import model.Shape;
import model.ShapeCollection;


public class MainUI extends JFrame {

  private JButton next;
  private JButton start;
  private JPanel toolbar;
  private JLabel label;
  private ChessBoard chessBoard;
  private boolean isStart = false;
  private boolean stop = false;
  public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
  public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
  public int celldim = 10;
  private int duration = 300;
  private int generation = 0;
  private int windowwidth = 500;
  private int windowheight = 500;

  /**
   * the main programm of the GUI.
   */

  @SuppressWarnings("unchecked")
  public MainUI() {
    setTitle("Game of Life");
    chessBoard = new ChessBoard();
    label = new JLabel("Generation:" + generation);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);

    next = new JButton("Next");
    start = new JButton("Start");
    toolbar = new JPanel();
    JComboBox<String> cmb1 = new JComboBox<>();
    cmb1.addItem("clear");
    Shape[] shapes = ShapeCollection.getShapes();
    for (int i = 0; i < shapes.length; i++) {
      cmb1.addItem(shapes[i].getName());
    }

    cmb1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (isStart == false) {
          String s = (String) cmb1.getSelectedItem();

          if (s.equals("clear")) {
            chessBoard.clearcompute();
            label.setText("Generation:" + 0);
          } else {
            chessBoard.clearcompute();
            chessBoard.shapecompute(s);
            label.setText("Generation:" + 0);
          }

        }
      }
    });
    MyItemListener lis = new MyItemListener();
    start.addActionListener(lis);
    next.addActionListener(lis);

    this.addComponentListener(new ComponentListener() {
      @Override
      public void componentResized(ComponentEvent e) {
        windowwidth = getWidth();
        windowheight = getHeight();
        chessBoard.resizecompute(windowwidth / celldim, windowheight / celldim);
      }

      @Override
      public void componentMoved(ComponentEvent e) {

      }

      @Override
      public void componentShown(ComponentEvent e) {

      }

      @Override
      public void componentHidden(ComponentEvent e) {

      }
    });
    JComboBox<String> cmb2 = new JComboBox<>();
    cmb2.addItem("big");
    cmb2.addItem("medium");
    cmb2.addItem("small");
    cmb2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String s = (String) cmb2.getSelectedItem();
        if (s.equals("medium")) {
          celldim = 5;
          int currentHeight = getHeight();
          int currentWidth = getWidth();
          int cellSizeMedium = 5;
          int numColumns = currentWidth / cellSizeMedium;
          int numRows = currentHeight / cellSizeMedium;
          chessBoard.resizecompute(numColumns, numRows);
        }
        if (s.equals("big")) {
          celldim = 10;
          int currentHeight = getHeight();
          int currentWidth = getWidth();
          int cellSizeMedium = 10;
          int numColumns = currentWidth / cellSizeMedium;
          int numRows = currentHeight / cellSizeMedium;
          chessBoard.resizecompute(numColumns, numRows);
        }
        if (s.equals("small")) {
          celldim = 3;
          int currentHeight = getHeight();
          int currentWidth = getWidth();
          int cellSizeMedium = 3;
          int numColumns = currentWidth / cellSizeMedium;
          int numRows = currentHeight / cellSizeMedium;
          chessBoard.resizecompute(numColumns, numRows);
        }

      }
    });
    JComboBox<String> cmb3 = new JComboBox<>();
    cmb3.addItem("slow");
    cmb3.addItem("fast");
    cmb3.addItem("hyper");
    cmb3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String s = (String) cmb3.getSelectedItem();
        if (s.equals("hyper")) {
          duration = 30;
        }
        if (s.equals("slow")) {
          duration = 300;
        }
        if (s.equals("fast")) {
          duration = 150;
        }
      }
    });
    toolbar.add(cmb1);
    toolbar.add(next);
    toolbar.add(start);
    toolbar.add(cmb2);
    toolbar.add(cmb3);
    toolbar.add(label);
    add(chessBoard);
    add(toolbar, BorderLayout.SOUTH);
    toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));


  }


  private class MyItemListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if (obj == next) {
        chessBoard.nextcompute();
        chessBoard.genoutput();
        label.setText("Generation:" + generation);
      }
      if (obj == start) {

        if (!isStart) {
          new Thread(new GameControlTask()).start();
          isStart = true;
          stop = false;

          start.setText("Stop");


        } else {

          stop = true;

          isStart = false;

          start.setText("Start");
        }

      }
    }

  }

  private class GameControlTask implements Runnable {

    @Override
    public void run() {
      while (!stop) {
        chessBoard.nextcompute();
        chessBoard.genoutput();
        label.setText("Generation:" + generation);
        try {

          TimeUnit.MILLISECONDS.sleep(duration);

        } catch (InterruptedException ex) {

          ex.printStackTrace();

        }
      }
    }
  }


  public class ChessBoard extends JPanel implements MouseListener {

    public Game game;

    /**
     * the class of the chessboard.
     */

    public ChessBoard() {
      Container p = getContentPane();
      p.setBackground(Color.GRAY);
      game = new Game(windowwidth / celldim, windowheight / celldim);
      setBackground(Color.gray);
      setVisible(true);
      setLayout(null);
      setResizable(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      addMouseListener(this);
      addMouseMotionListener(new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
          int x = e.getX();
          int y = e.getY();
          int cx = x / celldim;
          int cy = y / celldim;
          game.changeCellStatus(cx, cy);
          repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
      });

    }

    /**
     * the funktion of the painting.
     * @param g the object of the painting.
     */

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.LIGHT_GRAY);
      g.drawRect(0, 0, windowwidth, windowheight);

      for (int a = 0; a < game.getColumns(); a++) {
        for (int b = 0; b < game.getRows(); b++) {
          if (game.getCellAt(a, b).getAlive()) {
            g.setColor(Color.YELLOW);
            g.fillRect(a * celldim, b * celldim, celldim, celldim);
          }

        }
      }
      g.setColor(Color.LIGHT_GRAY);
      for (int i = 1; i < width / celldim; i++) {
        g.drawLine(i * celldim, 0, i * celldim, height);
      }
      for (int i = 1; i < height / celldim; i++) {
        g.drawLine(0, i * celldim, width, i * celldim);
      }


    }



    public void nextcompute() {
      game.next();
      repaint();
    }

    public void clearcompute() {
      game.clear();
      repaint();
    }

    /**
     * output the shape durch the name.
     * @param shape the name of the shape.
     */

    public void shapecompute(String shape) {
      game.shapeCompute(ShapeCollection.getShapeByName(shape));
      repaint();


    }

    /**
     * resize the chessboard with the column and the rows.
     * @param a the column.
     * @param b the rows.
     */

    public void resizecompute(int a, int b) {
      game.resize(a, b);
      repaint();

    }

    public void genoutput() {
      generation = game.getGenerations();

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
      int cx = x / celldim;
      int cy = y / celldim;
      game.changeCellStatus(cx, cy);
      repaint();


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
  }


  public static void main(String[] args) {
    MainUI a = new MainUI();
    a.setVisible(true);
  }


}