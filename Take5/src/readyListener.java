
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;

class readyListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
		Start.startFrame.getContentPane().removeAll();
		Start.startFrame.setVisible(false);
		OneName.player1name = OneName.name.getText();
		OneName.com1name    = OneName.name_com1.getText();
		OneName.com2name    = OneName.name_com2.getText();
		OneName.com3name    = OneName.name_com3.getText();
		PlayGame new_game = new PlayGame();
	}
}