
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

public class StartListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
		Start.startFrame.getContentPane().removeAll();
		Start.startButton.removeActionListener(this);
		Mode mode = new Mode();
		mode.chooseMode();
	}
}