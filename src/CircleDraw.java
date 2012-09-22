/*
Andrew Darwin
CSC 420: Graphical User Interfaces
Fall 2012
*/

//package csc420.hw2;

import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class CircleDraw
{
  private static void createAndShowGUI()
  {
    JFrame frame = new JFrame("Circle Draw");
    JSlider verticalSlider = new JSlider();
    JSlider horizontalSlider = new JSlider();
    Canvas canvas = new Canvas();
  }
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowGUI();
      }
    });
  }
}
