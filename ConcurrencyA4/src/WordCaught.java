/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
public class WordCaught implements Runnable{
    static Score score;
    static  WordRecord[] wr;
    static int i;
    static String text;
    WordCaught(Score s,WordRecord[] wr,int i,String t){
        this.score=s;
        this.wr=wr;
        this.i=i;
        this.text=t;
    }
    @Override
    public void run(){
        System.out.println(wr[i].getWord()+" "+ i+" "+text+" "+wr[i]);
        if(wr[i].matchWord(text)){
                          score.caughtWord(text.length());
                          wr[i].resetWord();
                          
                          
    }
        else{
            //System.out.println("hi");
        }
    }
}
