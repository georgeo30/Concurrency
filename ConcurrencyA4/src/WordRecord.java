
public class WordRecord {
	private String text;
	private  int x;
	private int y;
	private int maxY;
	private boolean dropped;
	
	private int fallingSpeed;
	private static int maxWait=10;
	private static int minWait=2;

	public static WordDictionary dict;
	

	/**
         * Constructor 1
         */
	WordRecord() {
		text="";
		x=0;
		y=0;	
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	/**
         * Constructor 2
         * @param text 
         */
	WordRecord(String text) {
		this();
		this.text=text;
	}
	/**
         * constructor 3
         * @param text
         * @param x
         * @param maxY 
         */
	WordRecord(String text,int x, int maxY) {
		this(text);
		this.x=x;
		this.maxY=maxY;
	}
	
// all getters and setters must be synchronized
        /**
         * setting y position
         * @param y 
         */
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true;
		}
		this.y=y;
	}
	/**
         * setting x position 
         * @param x 
         */
	public synchronized  void setX(int x) {
		this.x=x;
	}
	/**
         * setting word
         * @param text 
         */
	public synchronized  void setWord(String text) {
		this.text=text;
	}
        /**
         * getting word
         * @return 
         */
	public synchronized  String getWord() {
		return text;
	}
	/**
         * getting x
         * @return 
         */
	public synchronized  int getX() {
		return x;
	}	
	/**
         * getting y
         * @return 
         */
	public synchronized  int getY() {
		return y;
	}
	/**
         * getting speed
         * @return 
         */
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}
        /**
         * setting c=x and y positions
         * @param x
         * @param y 
         */
	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
        /**
         * reseting position back to the top
         */
	public synchronized void resetPos() {
		setY(0);
	}
        /**
         * reseting word by getting a new words.
         * new fall speed
         */
	public synchronized void resetWord() {
		resetPos();
		text=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());

	}
	/**
         * method to check if word is found
         * @param typedText
         * @return 
         */
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.text)) {
			resetWord();
			return true;
		}
		else
			return false;
	}
	
        /**
         * drops words down
         * @param inc 
         */
	public synchronized  void drop(int inc) {
		setY(y+inc);
	}
	/**
         * boolean to check if dropped
         * @return 
         */
	public synchronized  boolean dropped() {
		return dropped;
	}

}
