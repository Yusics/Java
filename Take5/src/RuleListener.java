package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

class RuleListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Start.startFrame.getContentPane().removeAll();
			Start.ruleButton.removeActionListener(this);
			Rule rule = new Rule();
		}
	}