
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;


public class homeButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
		Start.startFrame.getContentPane().removeAll();
		Start start = new Start();
		start.startGame();
	}
}


