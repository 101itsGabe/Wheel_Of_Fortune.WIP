import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Random;

public class WOFCharPanel extends JPanel implements ActionListener
{
  private BufferedImage backImg;  
  private int tRowx, tRowy,rectArea;
  private int pixel = 0,charYPixel = 0;
  private String st;
  private String charString;
  private Color WOFGreen = new Color(25,138,111);
  private ArrayList<Point> coords;
  private JButton startButton, solveButton, constButton;
  protected Timer timer;		
  protected Random rand;
  protected ArrayList<Integer>curChar; 
  private boolean ifSolved;
  protected JTextField solveField;
  protected JTextField charField;
  private int gameBal = 10000, perChar;

  public WOFCharPanel()
  {
    try{
    backImg = ImageIO.read(new File("wheelbackground.jpeg"));}
    catch(IOException e){}
    coords = new ArrayList<Point>();
    curChar = new ArrayList<Integer>();
    timer = new Timer(2000,this);
    rand = new Random();
    startButton = new JButton("Start!");
    solveButton = new JButton("Solve!");
    solveField = new JTextField();
    charField  = new JTextField();
    constButton = new JButton("Guess a Char!");

    add(startButton);
    add(solveButton);
    add(solveField);
    add(charField);
    add(constButton);

    solveButton.setVisible(false);
    constButton.setVisible(false);
    solveField.setVisible(false);
    charField.setVisible(false);

    startButton.addActionListener(new ActionListener()
    {
     public void actionPerformed(ActionEvent e)
     {
      timer.start();
      startButton.setVisible(false);
      solveButton.setVisible(true);
      constButton.setVisible(true);
     }
    });


   constButton.addActionListener(new ActionListener()
    {
     public void actionPerformed(ActionEvent e)
     {
      timer.stop();
      charField.setVisible(true);
      solveButton.setVisible(false);
      constButton.setVisible(false);
     }
    });

   charField.addActionListener(new ActionListener()
    {
     public void actionPerformed(ActionEvent e)
     {
      String constText = charField.getText(); 
      boolean onBoard = false;
  
     for(int i = 0; i < curChar.size(); i++)
      {
        String charCheck = Character.toString(st.charAt(curChar.get(i)));
        if(charCheck.compareTo(constText) == 0)
        {
         JOptionPane.showMessageDialog(null,"Character already chosen, You Lost $500");
         solveButton.setVisible(true);
         constButton.setVisible(true);
         charField.setVisible(false);
         onBoard = true;
         break;
        }
      }
     
      if(!onBoard)
      {
        for(int j = 0; j < st.length(); j++)
        {
          String charAdd = Character.toString(st.charAt(j));
          if(charAdd.compareTo(constText) == 0)
           curChar.add(j);
        }
        System.out.println("curChar after: " + curChar);

        JOptionPane.showMessageDialog(null,"Added to the board!");
        solveButton.setVisible(true);
        constButton.setVisible(true);
        charField.setVisible(false);
        repaint();
      }//end of if
      }//end of actionPerformed
    });


   solveButton.addActionListener(new ActionListener()
    {
     public void actionPerformed(ActionEvent e)
     {
      timer.stop();
      solveButton.setVisible(false);
      solveField.setVisible(true);
      constButton.setVisible(false);
      repaint();
     }
    });
  
    solveField.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
       if(st.compareTo(solveField.getText()) == 0)
       {
         JOptionPane.showMessageDialog(null,"Correct! You Win!");
         for(int i = 0; i < st.length(); i++)
         {
	   if(curChar.contains(i))
            continue;
           else
            curChar.add(i);
         }
         repaint();
         ifSolved = true;
       }
       else
       {
        JOptionPane.showMessageDialog(null,"Incorrect");
        solveButton.setVisible(true);
       solveField.setVisible(false);
       constButton.setVisible(true);
        ifSolved = false;
       }
      }
    });
    

  }
 
  public ArrayList<Point> getList()
  {
   return coords;
  }
 
  public void setString(String s)
  {
   st = new String(s);
   perChar = st.length()/gameBal;
  }

  public boolean beenSolved()
  {
    return ifSolved;
  }
  public int getStLen()
  {
    return st.length();
  }
  
 public void actionPerformed(ActionEvent e)
 {
   if(curChar.isEmpty())
    curChar.add(rand.nextInt(st.length()));
   else
   {
    Integer temp;
    do
    {
     temp = rand.nextInt(st.length());
    if(curChar.size() == st.length())
    {
     timer.stop();
     break;
    }
    }while(curChar.contains(temp));

    curChar.add(temp);
   }
   repaint();
 
 }

  

  public void paintComponent(Graphics g)
  {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(backImg,0,0,getWidth(),getHeight(),null);   
    g2d.setStroke(new BasicStroke(2));
    tRowy =(int)(getHeight() * 0.13);
    rectArea = ((int)(getWidth() * 0.15) + (int)(getHeight() * 0.15))/5;

    String rowY = ("Category: Spongebob Quotes");
    g2d.setFont(new Font("Sledge Hammer",Font.BOLD, rectArea - 3));
    g2d.drawString(rowY, getWidth()/4,(int)(getHeight() * 0.1));
    coords.clear();

   

     for(int c = 0; c < 4; c++)
     {
       if(c != 0){ 
          tRowy += (int)(getWidth() * 0.1); };

       g2d.setColor(WOFGreen);
       tRowx = (int)(getWidth() * 0.15);
       pixel = 0;
      
       if(c == 1 || c == 2){
        g2d.fillRect(tRowx - (int)(getWidth() * 0.058),tRowy,(int)(getWidth() * 0.816),(int)(getHeight() * 0.15));        
       
         for(int i = 0; i < 14; i++)
      {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(tRowx + pixel - (int)(getWidth() * 0.058),tRowy ,(int)(getWidth() * 0.058),(int)(getHeight() * 0.15));         //0.058 is 0.70 divided by the number of boxes I want
        pixel += (int)(getWidth() * 0.058);     //Moves the boxes right 50 pixels
      }
       }


       else{
        g2d.fillRect(tRowx,tRowy,(int)(getWidth() * 0.70),(int)(getHeight() * 0.15));
     for(int i = 0; i < 12; i++)
      {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(tRowx + pixel,tRowy ,(int)(getWidth() * 0.058),(int)(getHeight() * 0.15));		//0.058 is 0.70 divided by the number of boxes I want
        pixel += (int)(getWidth() * 0.058);	//Moves the boxes right 50 pixels
      }
    }

    }//end of drawing Char Containers for loop
   
    int charCounter = 0;
    int spaceCounter = 0;
    pixel = 0;
    tRowy = (int)(getHeight() * 0.25);
    g2d.setFont(new Font("Sledge Hammer",Font.BOLD, rectArea));

    for(int i = 0; i < st.length(); i ++)
    {
      if(i != 0 && charCounter != 0)
       pixel += (int)(getWidth() * 0.058);

      if(st.charAt(i) != ' ')					//keep track of how many letter inbetween spaces
       spaceCounter++;
      else
      {
       int checkCounter = i + 1;				//counter to check all the characters after the space
       int afc = 0;						//different counter to keep track of how many chars until end of row
 
       for(int j = charCounter; j < 12; j++)			//goes to 11 to see how many spaces left in row
       {
        if(st.charAt(checkCounter) != ' ' && checkCounter < st.length() - 1)	
        {
         checkCounter++;
         afc++;
        }
       }
       
       if(charCounter + afc > 11)				//if where the character counter is at plus the afc counter is past 11 reset and go to next row
       {
         charCounter = 0;
         pixel = 0;
         tRowy += (int)(getWidth() * 0.1);
         spaceCounter = 0;
         coords.add(new Point(tRowx + pixel + (int)(getWidth() * 0.01) , tRowy + (int)(getHeight() * 0.001)));
         continue;
       } 
       spaceCounter = 0; 
      }//end of else statement
     
       if (st.charAt(i) == ' ' && charCounter == 12)		//if counter is at 12 and a space reset and go to next row
       {
        charCounter = 0;
        pixel = 0;
        tRowy += (int)(getWidth() * 0.1);
       }
 

     else
     {
      g2d.setColor(Color.BLACK);   
      //g2d.drawString(charString,tRowx + pixel + (int)(getWidth() * 0.01) ,tRowy + (int)(getHeight() * 0.001));
      coords.add(new Point(tRowx + pixel + (int)(getWidth() * 0.01) , tRowy + (int)(getHeight() * 0.001)));			 //adding each character cords to a list that Game can use
      charCounter++;
     }

   }//end of for loop
  
   for(int i = 0; i < curChar.size(); i++)
   {
    charString = new String(Character.toString(st.charAt(curChar.get(i))));
    g2d.drawString(charString,coords.get(curChar.get(i)).x,coords.get(curChar.get(i)).y);
   }
    startButton.setBounds((int)(getWidth() * 0.30),(int)(getHeight() * 0.80),(int)(getWidth() * 0.35),(int)(getHeight() * 0.15));
    solveButton.setBounds((int)(getWidth() * 0.25),(int)(getHeight() * 0.80),(int)(getWidth() * 0.30),(int)(getHeight() * 0.15));
    constButton.setBounds((int)(getWidth() * 0.55),(int)(getHeight() * 0.80),(int)(getWidth() * 0.3),(int)(getHeight() * 0.15));
    solveField.setBounds((int)(getWidth() * 0.22),(int)(getHeight() * 0.80),(int)(getWidth() * 0.58),(int)(getHeight() * 0.1));
    solveField.setFont(new Font("Sledge Hammer",Font.BOLD,(int)(getHeight() * 0.028)));
    charField.setFont(new Font("Sledge Hammer",Font.BOLD,(int)(getHeight() * 0.028)));
    charField.setBounds((int)(getWidth() * .42),(int)(getHeight() * 0.80),(int)(getWidth() * 0.1),(int)(getHeight() * 0.1));
                                            
 }//end of paint component


  

     
}


