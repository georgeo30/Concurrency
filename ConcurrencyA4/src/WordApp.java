

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


import java.util.Scanner;

//model is separate from the view.

public class WordApp {
//shared variables
	static int noWords=4;
	static int totalWords;

   	static int frameX=1000;
	static int frameY=600;
	static int yLimit=480;

	static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually

	static WordRecord[] words;
	static volatile boolean done;  //must be volatile
	static 	Score score = new Score();
        public  static JLabel missed;
        public static JLabel caught;
        public static JLabel scr;
	static WordPanel w;
        static wordEntry we=new wordEntry();
	public static JButton startB;
	static String textE;
        static int current;
        
        static Thread newThread;
	public static void setupGUI(int frameX,int frameY,int yLimit) {
		// Frame init and dimensions
    	JFrame frame = new JFrame("WordGame"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setSize(frameX,frameY);
 
    	
		w = new WordPanel(words,yLimit);
		w.setSize(frameX,yLimit+100);
	    g.add(w);
	    
	    
	    JPanel txt = new JPanel();
	    txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS)); 
	    caught =new JLabel("Caught: " + score.getCaught() + "    ");
	    missed =new JLabel("Missed:" + score.getMissed()+ "    ");
            scr =new JLabel("Score:" + score.getScore()+ "    ");    
	    txt.add(caught);
	    txt.add(missed);
	    txt.add(scr);
    
	    //[snip]
	    final JTextField textEntry = new JTextField("",20);
	   textEntry.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent evt) {
	          String text = textEntry.getText();
	          //[snip]
                  textE=text;
	          Thread t=new Thread(we);
                  t.start();
                  
                
                 
                   
                  scr.setText("Score:" + score.getScore()+ "    ");
                  caught.setText("Caught: " + score.getCaught() + "    ");
                  missed.setText("Missed:" + score.getMissed()+ "    ");
                 
                  
                  textEntry.setText("");
	          textEntry.requestFocus();
	      }
	    });
	   
	   txt.add(textEntry);
	   txt.setMaximumSize( txt.getPreferredSize() );
	   g.add(txt);
	    
	    JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
	   	startB = new JButton("Start");;
		
			// add the listener to the jbutton to handle the "pressed" event
			startB.addActionListener(new ActionListener()
                                
		    {
		      public void actionPerformed(ActionEvent e)
		      {
		    	  //[snip]
                          
                          w.check=true;
                          
                          newThread=new Thread(w);
                          newThread.start();
                          startB.setEnabled(false);
		    	  textEntry.requestFocus(); //return focus to the text entry field
		      }
		    });
		JButton pauseB = new JButton("Pause");;
			
				// add the listener to the jbutton to handle the "pressed" event
				pauseB.addActionListener(new ActionListener()
			    {
			      public void actionPerformed(ActionEvent e)
			      {
                                 w.check=false; 
			    	  //[snip]
                                 startB.setEnabled(true); 
			      }
			    });
                JButton endB = new JButton("End");;
			
				// add the listener to the jbutton to handle the "pressed" event
				endB.addActionListener(new ActionListener()
			    {
			      public void actionPerformed(ActionEvent e)
			      {
			    	  //[snip]
                                  w.check=false;
                                JOptionPane.showMessageDialog (null, "Game Stopped \nYour score is " + score.getScore()+"\n"+"You caught " + score.getCaught()+" words \n"+"You missed " + score.getMissed()+" words", "Results",JOptionPane.PLAIN_MESSAGE);
                                  //score.missedWords=0;
                                  missed.setText("Missed:" + score.getMissed()+ "    ");
                                  newThread.interrupt();
                                  
                                  
                                  startB.setEnabled(true);
                                  score.resetScore();
                                  for(int i=0;i<noWords;i++){
                                  words[i].resetPos();
                                  }
                                  missed.setText("Missed:" + score.getMissed()+ "    ");
                                  scr.setText("Score:" + score.getScore()+ "    ");
                                  caught.setText("caught:" + score.getCaught()+ "    ");
                                 
                                     
			      }
			    });
                                //exit button
                JButton quitB = new JButton("Quit");;
			
				// add the listener to the jbutton to handle the "pressed" event
				quitB.addActionListener(new ActionListener()
			    {
			      public void actionPerformed(ActionEvent e)
			      {
			    	  //[snip]
                                  frame.dispose();
                                  System.exit(0);
			      }
			    });
		
		b.add(startB);
		b.add(pauseB);
                b.add(endB);
                b.add(quitB);
                
		
		g.add(b);
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
       	//frame.pack();  // don't do this - packs it into small space
        frame.setVisible(true);

		
	}

	
public static String[] getDictFromFile(String filename) {
		String [] dictStr = null;
		try {
			Scanner dictReader = new Scanner(new FileInputStream(filename));
			int dictLength = dictReader.nextInt();
			//System.out.println("read '" + dictLength+"'");

			dictStr=new String[dictLength];
			for (int i=0;i<dictLength;i++) {
				dictStr[i]=new String(dictReader.next());
				//System.out.println(i+ " read '" + dictStr[i]+"'"); //for checking
			}
			dictReader.close();
		} catch (IOException e) {
	        System.err.println("Problem reading file " + filename + " default dictionary will be used");
	    }
		return dictStr;

	}

	public static void main(String[] args) {
    	
		//deal with command line arguments
                Scanner in=new Scanner(System.in);
                totalWords=Integer.parseInt(in.nextLine());
                noWords=Integer.parseInt(in.nextLine());
		//totalWords=Integer.parseInt(args[0]);  //total words to fall
		//noWords=Integer.parseInt(args[1]); // total words falling at any point
		assert(totalWords>=noWords); // this could be done more neatly
                String fileN=in.nextLine();
                String[] tmpDict=getDictFromFile(fileN); 
		//String[] tmpDict=getDictFromFile(args[2]); //file of words
		if (tmpDict!=null)
			dict= new WordDictionary(tmpDict);
		
		WordRecord.dict=dict; //set the class dictionary for the words.
		
		words = new WordRecord[noWords];  //shared array of current words
		
		//[snip]
		
		setupGUI(frameX, frameY, yLimit);  
    	//Start WordPanel thread - for redrawing animation

		int x_inc=(int)frameX/noWords;
	  	//initialize shared array of current words

		for (int i=0;i<noWords;i++) {
                    
			words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
		}
                
               


	}
        
        
        public static class wordEntry implements Runnable{
           @Override
           public void run(){
               
                      
               for(int current=0;current<noWords;current++){
               if(words[current].matchWord(getText())){
                   synchronized(score){
                         if(score.getTotal()>=totalWords){
                             
                                w.check=false;
                                newThread.interrupt();
                               
                                JOptionPane.showMessageDialog (null, "Game Over \nYour score is " + score.getScore()+"\n"+"You caught " + score.getCaught()+" words \n"+"You missed " + score.getMissed()+" words", "Results",JOptionPane.PLAIN_MESSAGE);
                                
                                 
                                 score.resetScore();
                                  missed.setText("Missed:" + score.getMissed()+ "    ");
                                  scr.setText("Score:" + score.getScore()+ "    ");
                                  caught.setText("caught:" + score.getCaught()+ "    ");
                                  
                                  startB.setEnabled(true);
                                  
                                  
                                  
                                  for(int i=0;i<WordApp.noWords;i++){
                                  words[i].resetPos();
                                  }
                                
                            } 
                         else{
                          
                          score.caughtWord(textE.length());
                          
                          words[current].resetWord();
                          missed.setText("Missed:" + score.getMissed()+ "    ");
                                  scr.setText("Score:" + score.getScore()+ "    ");
                                  caught.setText("caught:" + score.getCaught()+ "    ");
                         }
                      }
               }
               }    
        }
        synchronized String getText(){
            return textE;
        }
}
}

