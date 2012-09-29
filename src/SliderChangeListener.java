/*
Andrew Darwin
CSC 420: Graphical User Interfaces
Fall 2012
*/

//package csc420.hw2;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingUtilities;

public class SliderChangeListener implements ChangeListener
{
  public static boolean bypassDrawing = false;
  public void stateChanged(ChangeEvent e)
  {
    if (!bypassDrawing)
    {
      CircleDraw.drawCircle();
    }
  }
}
