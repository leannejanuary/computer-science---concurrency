import javax.swing.*;
import java.awt.*;

public class MessageView extends JPanel {
	
	@Override
	public void paintComponent(Graphics g){
		int width = getWidth();
		int height = getHeight();
		g.clearRect(0,0,width,height);

		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.PLAIN, 36));
		String text = "Game Over";
		g.drawString(text,(width/2)-120,height/3);
		g.setFont(new Font("Helvetica", Font.PLAIN, 24));
		g.drawString(WordApp.score.toString(), (width/2)-200,height/2);
	}
}
