import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cell extends JComponent {

  private static final long serialVersionUID = 42l;

  int x,y;
  Color c;
  public static int size = 10;

  public Cell(int x, int y, int c){
    this.x = x;
    this.y = y;
    this.c = intToCol(c);
    setBounds(x, y, size, size);

    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        printDets();
        if(getCol().equals(Color.DARK_GRAY)){
          System.out.println("Clear");
        }
      }
    });
  }

  public void printDets(){
    System.out.println(x + " " + y + " " + c.toString());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(c);
    g.fillRect(0, 0, size, size);
  }

  public void setCol(int c){
    this.c = intToCol(c);
    repaint();
  }

  public Color getCol(){
    return this.c;
  }

  public void setPos(int x, int y){
    this.x = x;
    this.y = y;
    setPos(x, y);
  }

  public Color intToCol(int c){
    switch(c){
      case 0:
        return Color.BLACK;

      case 1:
        return Color.WHITE;

      case 2:
        return Color.RED;

      case 3:
        return Color.BLUE;

      case 4:
        return Color.PINK;

      case 5:
        return Color.LIGHT_GRAY;

      case 6:
        return Color.DARK_GRAY;

      case 7:
        return Color.GREEN;

      case 8:
        return Color.ORANGE;

      case 9:
        return Color.MAGENTA;

      default:
        return Color.WHITE;

    }
  }



  // public void paint(Graphics g) {
  //   Graphics2D g2 = (Graphics2D) g;
  //   g2.drawImage(img, 0, 0, this);
  // }


}
