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
    public static int i;
    public static WordPanel wp;
    wordThreads(int i,WordPanel wp){
        this.i=i;
        this.wp=wp;
        
    }
    @Override
    public void run(){
         try{
                            
                            wp.words[i].drop(wp.words[i].getSpeed()/50);
                            //System.out.println(wp.words[i]+"   "+i);
                            wp.repaint();
                            //Thread.sleep(5000);
                            //System.out.println(words[i].getWord()+" : "+words[i].getY());
                        }
                        catch(Exception e){
                            
                            
                        }
                        if(wp.words[i].getY()>=wp.maxY){
                            wp.words[i].resetWord();
                        }
                       
    }
}
