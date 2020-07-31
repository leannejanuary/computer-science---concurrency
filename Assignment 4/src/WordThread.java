public class WordThread implements Runnable {
	int noWords;
	WordRecord word;

	WordThread(WordRecord word, int noWords)  {
		this.noWords = noWords;
		this.word = word;
	}

	@Override
	public void run(){
		float inc = word.getSpeed()/(float) WordApp.yLimit;
		word.drop(inc);

		if (word.getY() >= WordApp.yLimit-10){
			word.resetWord();
			WordApp.score.missedWord();
		}
	}
}
