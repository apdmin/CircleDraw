/*
Andrew Darwin
CSC 420: Graphical User Interfaces
Fall 2012
*/

//package csc420.hw2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JWindow;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class CircleDraw
{
  private static JFrame frame;





  private static void createAndShowGUI()
  {
    frame = new JFrame("Circle Draw");
    frame.setSize(new Dimension(800, 600));
    frame.setLocationRelativeTo(null);

    addComponentsToPane(frame.getContentPane());
    frame.setVisible(true);
  }





  private static void addComponentsToPane(Container contentPane)
  {
    Canvas canvas = new Canvas()
    {
      private int x, y, diameter;
      private boolean filled;
      private Color color;

      public void setCircleCoordinates(int x, int y)
      {
        this.x = x;
        this.y = y;
      }
      public void setCircleDiameter(int diameter) { this.diameter = diameter; }
      public void setFilled(boolean filled) { this.filled = filled; }
      public void setColor(int color)
      {
        switch(color)
        {
          case 0:
            this.color = Color.red;
            break;
          case 1:
            this.color = Color.blue;
            break;
          default:
            this.color = Color.black;
        }
      }

      public void paint(Graphics g)
      {
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        g.setColor(color);
        if (filled)
        {
          g.fillOval(x, y, diameter, diameter);
        }
        else
        {
          g.drawOval(x, y, diameter, diameter);
        }
      }
    };



    SpringLayout frameLayout = new SpringLayout();
    frame.setLayout(frameLayout);
    JPanel inputPanel = new JPanel();
    SpringLayout inputPanelLayout = new SpringLayout();
    inputPanel.setLayout(inputPanelLayout);

    JFrame secondaryFrame = new JFrame("Color Chooser");
    secondaryFrame.setAlwaysOnTop(true);
    secondaryFrame.setResizable(false);
    //secondaryFrame.setDefaultLookAndFeelDecorated(true);
    JColorChooser colorChooser = new JColorChooser(Color.black);
    secondaryFrame.add(colorChooser);
    secondaryFrame.pack();
    secondaryFrame.setLocationRelativeTo(null);
    secondaryFrame.setVisible(true);


    JSlider leftSlider = new JSlider();
    leftSlider.setOrientation(SwingConstants.VERTICAL);
    /*
    JSlider rightSlider = new JSlider();
    rightSlider.setOrientation(SwingConstants.VERTICAL);
    JSlider topSlider = new JSlider();
    */
    JSlider bottomSlider = new JSlider();
    JSlider sizeSlider = new JSlider();
    JButton showButton = new JButton("Show");
    showButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    JButton colorButton = new JButton("Color Chooser");


    //Layout Setup
    int cornerSquare = 10;
    int sliderYMargin = 14;
    int sliderXMargin = 13;
    int xMargin = 5;
    int yMargin = 5;

    frameLayout.putConstraint(SpringLayout.SOUTH, colorButton, -1*yMargin, SpringLayout.SOUTH, contentPane);
    frameLayout.putConstraint(SpringLayout.WEST, colorButton, xMargin, SpringLayout.WEST, contentPane);

    frameLayout.putConstraint(SpringLayout.SOUTH, showButton, -1*yMargin, SpringLayout.SOUTH, contentPane);
    frameLayout.putConstraint(SpringLayout.EAST, showButton, -1*xMargin, SpringLayout.EAST, contentPane);

    frameLayout.putConstraint(SpringLayout.SOUTH, sizeSlider, -1*yMargin, SpringLayout.SOUTH, contentPane);
    frameLayout.putConstraint(SpringLayout.EAST, sizeSlider, 0, SpringLayout.WEST, showButton);
    frameLayout.putConstraint(SpringLayout.WEST, sizeSlider, 0, SpringLayout.EAST, colorButton);

    frameLayout.putConstraint(SpringLayout.SOUTH, bottomSlider, 0, SpringLayout.NORTH, sizeSlider);
    frameLayout.putConstraint(SpringLayout.EAST, bottomSlider, -1*xMargin,  SpringLayout.EAST, contentPane);
    frameLayout.putConstraint(SpringLayout.WEST, bottomSlider, -1 * sliderXMargin,  SpringLayout.EAST, leftSlider);


    frameLayout.putConstraint(SpringLayout.NORTH, leftSlider, yMargin, SpringLayout.NORTH, contentPane);
    frameLayout.putConstraint(SpringLayout.SOUTH, leftSlider, sliderYMargin, SpringLayout.NORTH, bottomSlider);
    frameLayout.putConstraint(SpringLayout.WEST, leftSlider, xMargin, SpringLayout.WEST, contentPane);

    frameLayout.putConstraint(SpringLayout.NORTH, canvas, sliderYMargin+yMargin, SpringLayout.NORTH, contentPane);
    frameLayout.putConstraint(SpringLayout.SOUTH, canvas, 0, SpringLayout.NORTH, bottomSlider);
    frameLayout.putConstraint(SpringLayout.EAST, canvas, -1*sliderXMargin-xMargin, SpringLayout.EAST, contentPane);
    frameLayout.putConstraint(SpringLayout.WEST, canvas, 0, SpringLayout.EAST, leftSlider);

    frameLayout.putConstraint(SpringLayout.SOUTH, inputPanel, 0, SpringLayout.SOUTH, contentPane);
    frameLayout.putConstraint(SpringLayout.EAST, inputPanel, 0, SpringLayout.EAST, contentPane);
    frameLayout.putConstraint(SpringLayout.WEST, inputPanel, cornerSquare, SpringLayout.WEST, contentPane);

    contentPane.add(canvas);
    contentPane.add(leftSlider);
    contentPane.add(bottomSlider);
    contentPane.add(sizeSlider);
    contentPane.add(colorButton);
    contentPane.add(showButton);
    /*
    contentPane.add(rightSlider, BorderLayout.LINE_END);
    contentPane.add(topSlider, BorderLayout.PAGE_START);
    */
    /*
    inputPanelLayout.putConstraint(SpringLayout.NORTH, bottomSlider, 0, SpringLayout.NORTH, inputPanel);
    inputPanelLayout.putConstraint(SpringLayout.EAST, bottomSlider, 0,  SpringLayout.EAST, inputPanel);
    inputPanelLayout.putConstraint(SpringLayout.WEST, bottomSlider, 0,  SpringLayout.WEST, inputPanel);

    inputPanelLayout.putConstraint(SpringLayout.NORTH, dropDown, 0, SpringLayout.SOUTH, bottomSlider);
    inputPanelLayout.putConstraint(SpringLayout.SOUTH, dropDown, 0, SpringLayout.SOUTH, inputPanel);
    inputPanelLayout.putConstraint(SpringLayout.EAST, dropDown, 0, SpringLayout.HORIZONTAL_CENTER, inputPanel);
    inputPanelLayout.putConstraint(SpringLayout.NORTH, showButton, 0, SpringLayout.SOUTH, bottomSlider);
    inputPanelLayout.putConstraint(SpringLayout.WEST, showButton, 0, SpringLayout.HORIZONTAL_CENTER, inputPanel);
    inputPanelLayout.putConstraint(SpringLayout.SOUTH, showButton, 0, SpringLayout.SOUTH, inputPanel);
    inputPanel.add(bottomSlider);
    //inputPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
    inputPanel.add(dropDown);
    inputPanel.add(showButton);
    */

    //contentPane.add(inputPanel, BorderLayout.PAGE_END);
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
