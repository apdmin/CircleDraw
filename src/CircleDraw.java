/*
Andrew Darwin
CSC 420: Graphical User Interfaces
Fall 2012
*/

//package csc420.hw2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class CircleDraw
{


  private static JFrame frame, colorChooserFrame;
  private static JColorChooser colorChooser;
  private static CustomCanvas canvas;
  private static JSlider leftSlider, bottomSlider, sizeSlider;
  private static JButton showButton;
  private static int sliderGradient;





  private static void createAndShowGUI()
  {
    frame = new JFrame("Circle Draw");
    frame.setPreferredSize(new Dimension(800, 600));
    sliderGradient = 2000;
    addComponentsToPane(frame.getContentPane());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }



  public static void resizeCircle(int amountToChangeRadius)
  {
    if (showButton.getText().equals("Hide"))
    {
      sizeSlider.setValue(canvas.getCircleRadius()-amountToChangeRadius);
    }
  }

  public static void positionAndDrawCircle(int x, int y)
  {
    if (showButton.getText().equals("Hide"))
    {
      Integer canvasHeight = new Integer(canvas.getHeight());
      Integer canvasWidth = new Integer(canvas.getWidth());
      Integer xCoord = new Integer(x);
      Integer yCoord = new Integer(y);
      double yProportion = yCoord.doubleValue()/canvasHeight.doubleValue();
      double xProportion = xCoord.doubleValue()/canvasWidth.doubleValue();
      leftSlider.setValue((int)((1-yProportion)*sliderGradient));
      bottomSlider.setValue((int)(xProportion*sliderGradient));
    }
  }
  public static void drawCircle()
  {
    if (showButton.getText().equals("Hide"))
    {
      Integer inputY = new Integer(leftSlider.getValue());
      Integer inputX = new Integer(bottomSlider.getValue());
      System.out.println(inputY);
      System.out.println(inputX);
      double yProportion = inputY.doubleValue()/sliderGradient;
      double xProportion = inputX.doubleValue()/sliderGradient;
      System.out.println("yProportion = " + yProportion);
      System.out.println("xProportion = " + xProportion);
      int canvasHeight = canvas.getHeight();
      int canvasWidth = canvas.getWidth();
      int xCoordinate = (int)(canvasWidth*xProportion);
      int yCoordinate = (int)(canvasHeight*(1-yProportion));
      Color color;
      if (colorChooser == null)
      {
        color = Color.black;
      }
      else
      {
        color = colorChooser.getColor();
      }
      drawCircle(xCoordinate, yCoordinate, sizeSlider.getValue(), color);
    }
  }
  public static void drawCircle(int x, int y)
  {
    drawCircle(x, y, sizeSlider.getValue(), null);
  }
  public static void drawCircle(int x, int y, int radius, Color color)
  {
    canvas.setCircleCoordinates(x, y);
    canvas.setCircleRadius(radius);
    if (color != null) canvas.setColor(color);
    canvas.setFilled(true);
    canvas.setHidden(false);
    canvas.repaint();
  }


  private static void showCircle()
  {
    if (showButton.getText().equals("Show"))
    {
      showButton.setText("Hide");
      drawCircle();
    }
    else if (showButton.getText().equals("Hide"))
    {
      canvas.setHidden(true);
      canvas.repaint();
      showButton.setText("Show");
    }
    else
    {
      //Throw some exception
    }
  }


  
  private static void displayColorChooser()
  {
    if (colorChooserFrame == null)
    {
      colorChooserFrame = new JFrame("Color Chooser");
      colorChooserFrame.setAlwaysOnTop(true);
      colorChooserFrame.setResizable(false);
      colorChooser = new JColorChooser(Color.black);
      colorChooser.getSelectionModel().addChangeListener(new ChangeListener()
      {
        public void stateChanged(ChangeEvent e)
        {
          SwingUtilities.invokeLater(new Runnable()
          {
            public void run()
            {
              CircleDraw.drawCircle();
            }
          });
        }
      });
      colorChooserFrame.add(colorChooser);
      colorChooserFrame.pack();
      colorChooserFrame.setLocationRelativeTo(null);
    }
    if (!colorChooserFrame.isVisible())
    {
      colorChooserFrame.setVisible(true);
    }
  }





  private static void addComponentsToPane(Container contentPane)
  {
    canvas = new CustomCanvas();
    SpringLayout frameLayout = new SpringLayout();
    frame.setLayout(frameLayout);
    /*
    JPanel inputPanel = new JPanel();
    SpringLayout inputPanelLayout = new SpringLayout();
    inputPanel.setLayout(inputPanelLayout);
    */



    leftSlider = new JSlider();
    leftSlider.setOrientation(SwingConstants.VERTICAL);
    leftSlider.setMinimum(0);
    leftSlider.setMaximum(sliderGradient);
    leftSlider.setValue(sliderGradient/2);
    /*
    JSlider rightSlider = new JSlider();
    rightSlider.setOrientation(SwingConstants.VERTICAL);
    JSlider topSlider = new JSlider();
    */
    bottomSlider = new JSlider();
    bottomSlider.setMinimum(0);
    bottomSlider.setMaximum(sliderGradient);
    bottomSlider.setValue(sliderGradient/2);
    sizeSlider = new JSlider();
    sizeSlider.setMinimum(5);
    sizeSlider.setMaximum(300);
    sizeSlider.setValue(50);
    showButton = new JButton("Show");
    showButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    JButton colorButton = new JButton("Color Chooser");


    //Add Listeners
    canvas.addMouseMotionListener(new MouseMotionListener()
    {
      public void mouseDragged(MouseEvent e)
      {
        Point point = e.getPoint();
        positionAndDrawCircle(point.x, point.y);
      }
      public void mouseMoved(MouseEvent e) {}
    });
    canvas.addMouseWheelListener(new MouseWheelListener()
    {
      public void mouseWheelMoved(MouseWheelEvent e)
      {
        int unitsToScroll = e.getUnitsToScroll();
        resizeCircle(unitsToScroll);
      }
    });
    SliderChangeListener changeListener = new SliderChangeListener();
    leftSlider.addChangeListener(changeListener);
    bottomSlider.addChangeListener(changeListener);
    sizeSlider.addChangeListener(changeListener);
    showButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        SwingUtilities.invokeLater(new Runnable()
        {
          public void run()
          {
            showCircle();
          }
        });
      }
    });

    colorButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        SwingUtilities.invokeLater(new Runnable()
        {
          public void run()
          {
            displayColorChooser();
          }
        });
      }
    });


    //Layout Setup
    //int cornerSquare = 10;
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

    /*
    frameLayout.putConstraint(SpringLayout.SOUTH, inputPanel, 0, SpringLayout.SOUTH, contentPane);
    frameLayout.putConstraint(SpringLayout.EAST, inputPanel, 0, SpringLayout.EAST, contentPane);
    frameLayout.putConstraint(SpringLayout.WEST, inputPanel, cornerSquare, SpringLayout.WEST, contentPane);
    */

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
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowGUI();
      }
    });
  }
}
