

import javax.swing.JOptionPane;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Georgeo
 */
public class wordThreads implements Runnable {
    public int i;
    public static WordPanel wp;
   
   /**
    * constructor that takes in i value and wordPanel object
    * @param i
    * @param wp 
    */
    wordThreads(int i,WordPanel wp){
        this.i=i;
        this.wp=wp;
        
        
    }
    
   /**
    * starts the threads
    * safety
    */ 
    void start(){
     Thread t = new Thread(this);
     t.start();
     
    }
     /**
      * run method to drop and check the missed words
      */
    @Override
    public void run(){
        
        while(wp.check){
            //System.out.print(i);
         try{
                            
            wp.words[i].drop(wp.words[i].getSpeed());
                            //System.out.println(wp.words[i]+"   "+i);
            wp.repaint();
                            
            Thread.sleep(60);
                            //System.out.println(words[i].getWord()+" : "+words[i].getY());
            }
            catch(Exception e){}
         
            if(wp.words[i].getY()>=wp.maxY){
                wp.words[i].resetWord();
                //missI();
                synchronized(WordApp.score){
                    if(wp.check){
                    WordApp.score.missedWord();
                    WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
                    
                    if(WordApp.score.getTotal()>=WordApp.totalWords){
                    wp.check=false;
                        WordApp.readFile();
                        int high;
                        if(WordApp.val>WordApp.score.getScore()){
                            high=WordApp.val;
                            
                        }
                        else{
                            high=WordApp.score.getScore();
                            WordApp.writeToFile(high+"");                           
                        }
                        
                    JOptionPane.showMessageDialog (null, "Game Over \nYour score is " + WordApp.score.getScore()+"\n"+"You caught " + WordApp.score.getCaught()+" words \n"+"You missed " + WordApp.score.getMissed()+" words \n"+"High Score: "+high, "Results",JOptionPane.PLAIN_MESSAGE);                     
            
                    WordApp.score.resetScore();
                       
                    WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
                    WordApp.scr.setText("Score:" + WordApp.score.getScore()+ "    ");
                    WordApp.caught.setText("caught:" + WordApp.score.getCaught()+ "    ");
                    WordApp.startB.setEnabled(true);
            
                    for(int i=0;i<WordApp.noWords;i++){
                        WordApp.words[i].resetPos();
                    }
                
                }}}
            }
        }
    }
        

   
}
