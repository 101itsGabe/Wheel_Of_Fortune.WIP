import java.util.*;
import java.lang.*;
import java.awt.Point;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WOFGame
{
  WOFCharPanel cp;
  WOFWheelPanel wp; 
  private int stLen;
  private boolean ifSolved;
  private Random rand;
  private int randLine;
  private String guess;

    public WOFGame()
    {
     rand = new Random();
     randLine = rand.nextInt(8) + 1;
     cp = new WOFCharPanel();
     wp = new WOFWheelPanel();

     try{
     guess = new String(Files.readAllLines(Paths.get("SpongebobQuotes")).get(randLine));
     }
     catch(IOException e)
     {System.out.println(e);}
     
     cp.setString(guess.toUpperCase());
    
     wp = new WOFWheelPanel();
     stLen = cp.getStLen();
    }


   public void setSolved(boolean b)
   {
     ifSolved = cp.beenSolved();
   }

   public boolean beenSolved()
   {
     return ifSolved;
   }
    

 

}



