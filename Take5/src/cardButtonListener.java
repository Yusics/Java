import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.util.*;
import java.lang.*;
import java.io.*;


class cardButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        PlayGame.countTurn++;
        if(PlayGame.countTurn == 10) PlayGame.over = true;
        for(int i = 0;i<PlayGame.cardPanel.size();i++){
            if(e.getSource() == PlayGame.cardLabel.get(i)){
                PlayGame.playerChoose = PlayGame.playerList[0].hand.get(i);
                System.out.println(PlayGame.playerChoose);
                PlayGame.cardLabel.get(i).setVisible(false);
                ImageIcon card = Dealer.setImage(PlayGame.playerList[0].hand.get(i)+1);
                PlayGame.playerLabel.get(0).setIcon(card);
                PlayGame.putCard();
            }
        }        
    }
}