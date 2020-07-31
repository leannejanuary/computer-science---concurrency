import javax.swing.*;

public class ScoreView implements Runnable {
	JLabel scr, caught, missed;

	ScoreView(JLabel s, JLabel c, JLabel m){
		this.scr = s;
		this.caught = c;
		this.missed = m;
	}

	@Override
	public void run(){
		scr.setVisible(true);
		caught.setVisible(true);
		missed.setVisible(true);

		scr.setText("Score: " + WordApp.score.getScore()+ "	");
		caught.setText("Caught: " + WordApp.score.getCaught()+ "     ");
		missed.setText("Missed: " + WordApp.score.getMissed()+ "     ");

		while(WordApp.score.getTotal() < WordApp.totalWords){
			if (WordApp.w.done){
				break;
			}
			scr.setText("Score: " + WordApp.score.getScore()+ "     ");
                caught.setText("Caught: " + WordApp.score.getCaught()+ "     ");
                missed.setText("Missed: " + WordApp.score.getMissed()+ "     ");

		}

		WordApp.endB.setVisible(false);
		WordApp.startB.setVisible(true);
		scr.setVisible(false);
		caught.setVisible(false);
		missed.setVisible(false);
		WordApp.stop();
	}
}
