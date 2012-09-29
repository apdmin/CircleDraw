/*
Andrew Darwin
CSC 420: Graphical User Interfaces
Fall 2012
*/

//package csc420.hw2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class CustomCanvas extends Canvas
{
  private int x, y, radius;
  private boolean filled = true;
  private boolean hidden = true;
  private Color color;

  public void setCircleCoordinates(int x, int y) { this.x = x; this.y = y; }
  public void setCircleRadius(int radius) { this.radius = radius; }
  public int getCircleRadius() { return radius; }
  public void setFilled(boolean filled) { this.filled = filled; }
  public boolean isFilled() { return filled; }
  public void setHidden(boolean hidden) { this.hidden = hidden; }
  public void setColor(Color color) { this.color = color; }

  public void paint(Graphics g)
  {
    if (!hidden)
    {
      g.setColor(CircleDraw.getColor());
      x = CircleDraw.getXCoord();
      y = CircleDraw.getYCoord();
      radius = CircleDraw.getRadius();
      if (filled)
      {
        g.fillOval(x-radius, y-radius, radius*2, radius*2);
      }
      else
      {
        g.drawOval(x-radius, y-radius, radius*2, radius*2);
      }
    }
    g.setColor(Color.black);
    g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
  }
}
