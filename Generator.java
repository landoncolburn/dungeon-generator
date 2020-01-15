/*
  Landon Colburn
  ©2019
*/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Generator {

  public static JFrame frame = new JFrame("Generator");
  public static Cell[][] cellList;
  public static Dimension size = new Dimension(600,600);
  public static int[][] intial = new int[50][50];

  public static void main(String[] args) {
    new Generator();
  }

  public Generator(){
    frame.setVisible(true);
    frame.setDefaultCloseOperation(3);
    frame.setPreferredSize(size);
    frame.repaint();
    frame.pack();

    generate(14);
    drawGrid(intial);
  }

  public void drawGrid(int[][] g){
    cellList = new Cell[g.length][g[0].length];
    for(int i = 0; i < g.length; i++){
      for(int j = 0; j < g[i].length; j++){
        cellList[i][j] = new Cell(i*10, j*10, g[i][j]);
        frame.add(cellList[i][j]);
      }
    }
    frame.repaint();
  }

  public int[][] createMap(int w, int h){
    int[][] m = new int[w][h];
    for(int i = 0; i < m.length; i++){
      for(int j = 0; j < m[i].length; j++){
        m[i][j] = 0;
      }
    }
    return m;
  }

  public static void generate(int n){
    int w,h,x,y;
    boolean bad = false;
    for(int i = 0; i < n; i++){
      w = (int)(Math.random()*5)+3;
      h = (int)(Math.random()*5)+3;
      x = (int)(Math.random()*50)+1;
      y = (int)(Math.random()*50)+1;
      bad = false;
      if(x+w < 49 && y+h < 49){
        for(int j = -1; j<=w; j++){
          for(int k = -1; k<=h; k++){
            if(intial[x+j][y+k] == 1){
              bad = true;
            }
          }
        }
        for(int j = 0; j<w; j++){
          for(int k = 0; k<h; k++){
            if(!bad){
              intial[x+j][y+k] = 1;
            }
          }
        }
        if(!bad){
          switch((int)(Math.random()*4)){
            case 0:
              intial[(int)(Math.random()*w)+x][y] = 2;
              break;
            case 1:
              intial[x+w-1][(int)(Math.random()*h)+y] = 2;
              break;
            case 2:
              intial[(int)(Math.random()*w)+x][y+h-1] = 2;
              break;
            case 3:
              intial[x][(int)(Math.random()*h)+y] = 2;
              break;
          }
        }
      } else {
        bad = true;
      }
      if(bad){
        i--;
      }
    }
  }

}
