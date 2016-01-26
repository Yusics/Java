package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;


class diffListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
		Start.startFrame.getContentPane().removeAll();
		if(e.getActionCommand() == "Easy"){
		    PlayGame.level = 0;
		}
		else if(e.getActionCommand() == "Medium") {
			PlayGame.level = 1;
		}
		else{
		    PlayGame.level = 2;
		}
		OneName play = new OneName();
		play.inputname();
	}
}