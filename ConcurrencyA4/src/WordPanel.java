

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;

public class WordPanel extends JPanel implements Runnable{
		public static volatile boolean done;
		public WordRecord[] words;
		private int noWords;
		public int maxY;
                static Graphics g;
                public boolean check;
		
		public void paintComponent(Graphics g) {
                    this.g=g;
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
                        repaint();
                    }
		   
		  }
		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;		
		}
		
                @Override
		public void run() {
                    
                    wordThreads[] t=new wordThreads[noWords];
                    
                    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
                        
                        
                        t[i]=new wordThreads(i,this);
                                               
                        t[i].start();
                        
                        repaint();
                    //g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);
                       /** try{
                            Thread.sleep(100);
                            words[i].drop(words[i].getSpeed()/30);
                            //System.out.println(words[i].getWord()+" : "+words[i].getY());
                        }
                        catch(Exception e){
                            
                            
                        }
                        if(words[i].getY()>=maxY){
                            words[i].setY(0);
                        }
                       
                   repaint();*/
                    
			//add in code to animate this
                        
		
                    }
		
                    
                }
                    
                

	}


