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
}
