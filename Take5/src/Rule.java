package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

public class Rule{
	private JPanel back;
	private JTextArea rule;
	private JButton backButton;
	public Rule(){
		back = new JPanel();
		Color color = new Color(44,62,80);
		back.setBackground(color);
		rule = new JTextArea("The goal is to be the player with the lowest number of points. To do this, the players should try to get rid of their cards, especially the ones with more cattle heads.6 Nimmt! is played using a special card deck that in the original version has a cattle head in the center of the card and a variable number of small cattle heads on the top. The cards are numbered 1 to 104, each giving 1, 2, 3, 5 or 7 points (i.e. cattle heads) to the person who picks it up.");
		back.add(rule);
		backButton = new JButton("Back");
		backButton.addActionListener(new homeButtonListener());
		back.add(backButton,BorderLayout.SOUTH);
		Start.startFrame.getContentPane().add(back);
		Start.startFrame.setSize(800,600);
		Start.startFrame.setVisible(true);
		Start.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 

}