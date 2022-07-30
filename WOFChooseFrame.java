import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class WOFChooseFrame extends JFrame
{ 
    private BufferedImage bckImg;
    private JButton wButton;
    private JButton gButton;
    private JPanel cards;
    CardLayout cardLayout;
    int w,h = getHeight();
    WOFGame gTest;
    boolean ifSolved;


    public  WOFChooseFrame()
    {
     super("Wheel Of Fortune");
     gTest = new WOFGame();
     cards = new JPanel(new CardLayout());
     wButton = new JButton("Show Wheel Frame");
     gButton = new JButton("Click here to Play!");

     cards.add(gTest.wp, "Wheel Panel");
     cards.add(gTest.cp, "Char Panel");
     CardLayout cardLayout = (CardLayout) cards.getLayout();
     add(cards);

     try{
       bckImg = ImageIO.read(new File("WOF.jpeg"));
     }
     catch(IOException e){  }
 
     JPanel bPanel = new JPanel()
     {
      
       public void paintComponent(Graphics g)
       {
         Graphics2D g2d = (Graphics2D) g;
         g2d.drawImage(bckImg,0,0,getWidth(),getHeight(),null);
         wButton.setBounds((int)(getWidth() * 0.4),(int)(getHeight() * 0.4),(int)(getWidth() * 0.25),(int)(getHeight() * 0.25));
         gButton.setBounds((int)(getWidth() * 0.4),(int)(getHeight() * 0.65),(int)(getWidth() * 0.25),(int)(getHeight() * 0.25));;
       }
  
      };	//end of bPanel
     bPanel.setBounds(0,0,this.getWidth(),this.getHeight());
     add(bPanel);
     bPanel.setLayout(null);

      wButton.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e)
        {
         cardLayout.show(cards,"Wheel Panel");
        }
     });
     bPanel.add(wButton);


      gButton.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent e)
       {
         cardLayout.show(cards,"Char Panel");
       }
     });
     bPanel.add(gButton);


     cards.add(bPanel,"Button Panel");

    add(cards);
    cardLayout.show(cards,"Button Panel");
   

    }	//end of constructor


    public void setSolved()
    {
     ifSolved = gTest.beenSolved();
    }



}
