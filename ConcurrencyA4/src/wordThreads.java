
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
     System.out.print(i);
    }
    @Override
    public void run(){
        
        while(true){
            //System.out.print(i);
         try{
                            
                            wp.words[i].drop(wp.words[i].getSpeed()/25);
                            //System.out.println(wp.words[i]+"   "+i);
                            wp.repaint();
                            
                            Thread.sleep(200);
                            //System.out.println(words[i].getWord()+" : "+words[i].getY());
                        }
                        catch(Exception e){
                            
                            
                        }
                        if(wp.words[i].getY()>=wp.maxY){
                            wp.words[i].resetWord();
                        }
        }
    }
}
