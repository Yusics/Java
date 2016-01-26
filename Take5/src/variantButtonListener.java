package take6;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;



class variantButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        for(int i=0; i < PlayGame.variantButton.size(); i++){
            if(e.getSource() == PlayGame.variantButton.get(i)){
                PlayGame.variantButton.get(i).setVisible(false);
                Dealer.varCount++;
                Dealer.pick.setText("Pick "+Integer.toString(10-Dealer.varCount)+" more Cards");
                PlayGame.variantChoose = i;
                PlayGame.playerList[0].chooseDeal(PlayGame.playerList[0]);
                Dealer.checkClicked();
            }
        }
    }
}