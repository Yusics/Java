
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

public class Mode{
	public static String mode;
	public void chooseMode(){
		Start.init_JLabel();
		Start.init_JButton();
		Start.init_JPanel();
		Start.init_model();
		Start.nimmt.setText("");
		Start.cow.setText("Choose Mode");
		Start.startButton.setText("Normal");
		Start.ruleButton.setText("Strategy");
		Start.startButton.addActionListener(new modeListener());
		Start.ruleButton.addActionListener(new modeListener());
		Start.cow.setBounds(335,45,180,20);
		Start.setHomeButton();
	}
	
}