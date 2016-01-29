
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import javax.swing.border.*;

class ShowCard{
    public static void showAllCards(){
        for(int row=0; row<4; row++) showOneRowCard(row);
    }
    public static void showOneRowCard(int row){
        PlayGame.rowPanel.get(row).removeAll();
        PlayGame.rowLabel.clear();
        for(int i = 0;i < 5;i++){
            JLabel label = new JLabel();
            PlayGame.rowPanel.get(row).add(label);
            PlayGame.rowLabel.add(label);
        }
        for(int i=0; i<PlayGame.table.get(row).size(); i++){
            int cardNum = (int)PlayGame.table.get(row).get(i);
            ImageIcon card = Dealer.setImage(cardNum);
            PlayGame.rowLabel.get(i).setIcon(card);
        }
    }

    public static String head(int cardId){
        return Integer.toString(getHead(cardId));
    }
    public static int getHead(int cardId){
        cardId += 1;
        if (cardId == 55) return 7;
        else if (cardId % 11 == 0) return 5;
        else if (cardId % 10 == 0) return 3;
        else if (cardId % 5  == 0) return 2;
        else return 1;
    }
}