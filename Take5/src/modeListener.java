package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

class modeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Start.startFrame.getContentPane().removeAll();
			if(e.getActionCommand() == "Normal") {
				Mode.mode = "Normal";
			}
			else{
			    Mode.mode = "Variant"; 
			}
			Start.startButton.removeActionListener(this);
			Start.ruleButton.removeActionListener(this);
			Difficulty level = new Difficulty();
			level.chooseDiff();
		}
	}