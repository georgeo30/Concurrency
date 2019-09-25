
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
public class wordThreads implements Runnable {
    public int i;
    public static WordPanel wp;
   
   
    wordThreads(int i,WordPanel wp){
        this.i=i;
        this.wp=wp;
        
        
    }
    
    
    void start(){
     Thread t = new Thread(this);
     t.start();
     
    }
     
    @Override
    public void run(){
        
        while(wp.check){
            //System.out.print(i);
         try{
                            
                            wp.words[i].drop(wp.words[i].getSpeed());
                            //System.out.println(wp.words[i]+"   "+i);
                            wp.repaint();
                            
                            Thread.sleep(50);
                            //System.out.println(words[i].getWord()+" : "+words[i].getY());
                        }
                        catch(Exception e){
                         
                            
                        }
                        if(wp.words[i].getY()>=wp.maxY){
                            if(WordApp.endCounter>=WordApp.totalWords){
                                WordApp.check=false;
                            }
                            else{
                            wp.words[i].resetWord();
                            synchronized(this){WordApp.endCounter++;}
                            missI();}
                        }
                        }
        }
        
    
    public synchronized void missI(){
        WordApp.score.missedWord();
        WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");

    } 
   
}
