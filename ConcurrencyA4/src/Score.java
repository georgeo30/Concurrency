
import java.util.concurrent.atomic.AtomicInteger;



public class Score {
	public AtomicInteger missedWords;
	private AtomicInteger caughtWords;
	private AtomicInteger gameScore;
	
        /**
         * constructor
         */
	Score() {
		missedWords=new AtomicInteger(0);
		caughtWords=new AtomicInteger(0);
		gameScore=new AtomicInteger(0);
	}
		
	// all getters and setters must be synchronized
	/**
         * returns missed value
         * @return 
         */
	public int getMissed() {
		return missedWords.get();
	}
        /**
         * returns caught value
         * @return 
         */
	public int getCaught() {
		return caughtWords.get();
	}
	/**
         * returns the caught + missed values
         * @return 
         */
	public int getTotal() {
            
		return (missedWords.get()+caughtWords.get());
	}
        /**
         * returns the game score
         * @return 
         */
	public int getScore() {
		return gameScore.get();
	}
	/**
         * increments the missed value
         */
	public void missedWord() {
		missedWords.getAndIncrement();
	}
        /**
         * increments the caught value, and adds to the score
         * @param length 
         */
	public synchronized void caughtWord(int length) {
		caughtWords.getAndIncrement();
		gameScore.getAndAdd(length);
	}
        /**
         * resets everything
         */
	public void resetScore() {
		caughtWords.set(0);
		missedWords.set(0);
		gameScore.set(0);
	}
}
