/*
  Landon Colburn
  ©2019
*/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrunkenStumble {

  public static JFrame frame = new JFrame("Generator");
  public static Cell[][] cellList;
  public static Dimension size = new Dimension(600,600);
  public static ArrayList<Walker> walkerList = new ArrayList<Walker>();

  public static int cellGridHeight;
  public static int cellGridWidth ;

  public static int counter = 0;

  public static void main(String[] args) {
    new DrunkenStumble();
  }

  public DrunkenStumble(){
    frame.setVisible(true);
    frame.setDefaultCloseOperation(3);
    frame.setPreferredSize(size);
    frame.repaint();
    frame.pack();

    initMap();
    addCells();
    spawnWalkers();
    walkWalkers();
    generateWalls();
    removeSingles();
    frame.repaint();
  }

  public void initMap(){
    int count = 0;
    cellGridHeight = (int)size.getHeight()/Cell.size;
    cellGridWidth = (int)size.getWidth()/Cell.size;
    cellList = new Cell[cellGridHeight][cellGridWidth];
    for(int i = 0; i<cellGridHeight; i++){
      for(int j = 0; j<cellGridWidth; j++){
        cellList[i][j] = new Cell(i*Cell.size, j*Cell.size, 6);
        count++;
      }
    }
    System.out.println(count + " cells created succesfully.");
  }

  public void addCells(){
    for(int i = 0; i<cellList.length; i++){
      for(int j = 0; j<cellList[i].length; j++){
        frame.add(cellList[i][j]);
      }
    }
    frame.repaint();
  }

  public void spawnWalkers(){
    int tx = (int)(Math.random()*20)+20;
    int ty = (int)(Math.random()*20)+20;
    for(int i = 0; i<(int)(Math.random()*4)+1; i++){
      Walker w = new Walker(tx,ty,(int)(Math.random()*4));
      walkerList.add(w);
    }
  }

  public void walkWalkers(){
    while(true){
      for(int j = 0; j<walkerList.size(); j++){
        Walker w = walkerList.get(j);
        w.walk();
        if(w.getX() >= 0 && w.getX() < 60 && w.getY() >= 0 && w.getY() < 60){
          cellList[w.getX()][w.getY()].setCol(1);
          counter++;
        }
      }
      if(counter > 400){
        break;
      }
      try{
        Thread.sleep(100);
      } catch(Exception e){

      }

    }
  }

  public void removeSingles(){
    for(int i = 0; i<cellList.length; i++){
      for(int j = 0; j<cellList[i].length; j++){
        if(cellList[i][j].getCol().equals(Color.BLACK)){
          int count = 0;
          for(int k = -1; k <= 1; k++){
            for(int l = -1; l <= 1; l++){
              if(cellList[i+k][j+l].getCol().equals(Color.WHITE)){
                count++;
              }
            }
          }
          if(count==8){
            cellList[i][j].setCol(1);
          }
        }
      }
    }
  }

  public void generateWalls(){
    for(int i = 0; i<cellList.length; i++){
      for(int j = 0; j<cellList[i].length; j++){
        if(i==0 || j==0 || i==cellList.length-1 || j==cellList[i].length-1){

        } else {
          if(cellList[i][j].getCol().equals(Color.WHITE)){
            for(int k = -1; k <= 1; k++){
              for(int l = -1; l <= 1; l++){
                if(cellList[i+k][j+l].getCol().equals(Color.DARK_GRAY)){
                  try{
                    Thread.sleep(10);
                  } catch(Exception e){

                  }
                  cellList[i+k][j+l].setCol(0);
                }
              }
            }
          }
        }
      }
    }
  }

}
