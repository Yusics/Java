package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import javax.swing.border.*;

public class Difficulty{
	public static JButton hard; 
	private JPanel diffPanel;

	public void chooseDiff(){
		hard = new JButton("Hard");
		hard.setBounds(490,50,130,80);
		hard.addActionListener(new diffListener());
		Color colb = new Color(155,210,217);
		hard.setBackground(colb);
		hard.setOpaque(true);
		SoftBevelBorder bevelraised = new SoftBevelBorder(BevelBorder.RAISED);
		hard.setBorder(bevelraised);
		Start.setFont(hard);
		Start.init_JLabel();
		Start.init_JButton();
		Start.init_JPanel();
		Start.init_model();
		Start.buttonPanel.add(hard);
		Start.nimmt.setText("");
		Start.cow.setText("Choose Difficulty");
		Start.startButton.setText("Easy");
		Start.ruleButton.setText("Medium");
		Start.startButton.setBounds(190,50,130,80);
		Start.ruleButton.setBounds(340,50,130,80);
		Start.startButton.addActionListener(new diffListener());
		Start.ruleButton.addActionListener(new diffListener());
		Start.cow.setBounds(320,45,180,20);
		Start.setHomeButton();
	}
}