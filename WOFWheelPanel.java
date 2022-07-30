import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Math;
import java.awt.geom.*;
import javax.swing.Timer;
import java.awt.event.*;

class WOFWheelPanel extends JPanel implements ActionListener
{
  private BufferedImage img,backImg;
  private JLabel n1,n2,n3; 
  private JPanel namePanel;
  private int wheelCir = (int)((getWidth() * 0.65) + (getHeight() * 0.65))/2;
  private int delay = 1;
  private int angle = 0;
  protected Timer timer;


  public WOFWheelPanel()
  {
    timer = new Timer(delay, this);
    timer.start();
   try{
     img = ImageIO.read(new File("wheel.png"));
     backImg = ImageIO.read(new File("wheelbackground.jpeg"));
    }
   catch(IOException e){
    }
  }


  public void paintComponent(Graphics g)
  {
   super.paintComponent(g);
   Graphics2D g2d = (Graphics2D)g;

   g2d.drawImage(backImg,0,0,getWidth(),getHeight(),null);
   g2d.rotate(Math.toRadians(angle += 2),getWidth()/2,getHeight()/2);
   g2d.drawImage(img,getWidth()/2 - wheelCir/2,getHeight()/2 - wheelCir/2,wheelCir,wheelCir,null);
  // add(wheelImg);
  }//end of paintComponent

 public void actionPerformed(ActionEvent e)
  {
    wheelCir = (int)((getWidth() * 0.65) + (getHeight() * 0.65))/2;
    repaint();    
  }

}
