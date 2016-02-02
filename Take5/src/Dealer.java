
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;

class Dealer{ //負責發牌
    public static int numOfCards = 104;
    public static int numOfPlayerCards = 10;
    public static int count = 0; //show which card will be dealt next
    public static ArrayList<Integer> deck = new ArrayList<>();
    public static int varCount = 0;
    public static JLabel pick;
    public static ImageIcon setImage(int i){
        ImageIcon card = new ImageIcon("../res/"+i+".png");
        Image image = card.getImage();
        Image newImage = image.getScaledInstance(50, 90, Image.SCALE_SMOOTH); // scale it the smooth way
        card = new ImageIcon(newImage);
        return card;
    }

    public static void dealCard(){ 
        count = 0;
        deck.clear();
        PlayGame.init_tablePanel();
        numOfCards = 104;
        setNewDeck(); //每次玩都重新洗牌
        System.out.println("Deal cards.");
        for (int i=0; i<PlayGame.numOfPlayers; i++){
            for (int j=0; j<numOfPlayerCards; j++){
                PlayGame.playerList[i].hand.add(deck.get(count));
                count++;
            }
            Collections.sort(PlayGame.playerList[i].hand); 
        }
        for(int i = 0; i <numOfPlayerCards; i++){
            ImageIcon card = Dealer.setImage(PlayGame.playerList[0].hand.get(i)+1);
            PlayGame.cardLabel.get(i).setIcon(card);
        }
        PlayGame.addListener();
        setTable(); //put four cards on the table
        ShowCard.showAllCards();
    }
    public static void dealVariant(){
        varCount = 0;
        deck.clear();
        pick = new JLabel("Pick "+Integer.toString(10-varCount)+" more Cards");
        pick.setForeground(Color.WHITE);
        Start.setFont(pick);
        PlayGame.variantPanel = new JPanel();
        Color color = new Color(44,62,80);
        PlayGame.tablePanel.setBackground(color);
        PlayGame.variantPanel.setBackground(color);
        PlayGame.variantPanel.setLayout(new GridLayout(4,11,10,20));
        for(int i =0; i < 44; i++){
            JPanel  panel  = new JPanel();
            panel.setBackground(color);
            ImageIcon card = Dealer.setImage(i+1);
            JButton button = new JButton(card);
            button.setFont(new Font("Cooper BlACK",Font.PLAIN,10));
            button.addActionListener(new variantButtonListener());
            panel.add(button);
            PlayGame.variantButton.add(button);
            PlayGame.variantPanel.add(panel);
        }
        pick.setBounds(250,600,200,60);
        PlayGame.variantPanel.setBounds(55,180,600,400);
        PlayGame.tablePanel.setLayout(null);
        PlayGame.tablePanel.add(PlayGame.variantPanel);
        PlayGame.tablePanel.add(Dealer.pick);
        setVariantDeck();
        setTable(); //put four cards on the table
        ShowCard.showAllCards();
        
    }

    public static void checkClicked(){
        if(Dealer.varCount == 10) {
            Dealer.chooseCard();
            PlayGame.variantPanel.setVisible(false);
        }
    }
    public static void chooseCard(){
        for(int i=0; i<10; i++){ //選十輪
            for(int j=1; j<PlayGame.numOfPlayers; j++){
                if (j!=0){ //若是人類玩家
                    PlayGame.playerList[j].chooseDeal(PlayGame.playerList[j]);
                }
                
            }
        }
        for(int i=0; i<PlayGame.numOfPlayers; i++)
            Collections.sort(PlayGame.playerList[i].hand); //先幫玩家把牌整理成由小到大
        for(int i = 0; i <numOfPlayerCards; i++){
            ImageIcon card = Dealer.setImage(PlayGame.playerList[0].hand.get(i)+1);
            PlayGame.cardLabel.get(i).setIcon(card);
        }
        PlayGame.addListener();
        numOfCards = 44;
        deck.clear();
        setTable(); //將四張牌放到桌子上當牌頭
        PlayGame.init_tablePanel();
        ShowCard.showAllCards();
    }
    
    public static void setNewDeck(){
        int[] randomOrder = new int[numOfCards];
        shuffleCard(randomOrder);
        for (int i=0; i<numOfCards; i++){
            deck.add(randomOrder[i]);
        }
    }

    public static void setVariantDeck(){
        for(int i =0; i<44; i++){
            deck.add(i);
        }

    }
    
    public static void shuffleCard(int[] shuffleOrder){ //Shuffler shuffle the deck 
        Random ran = new Random();
        int i, j, r;
        int initOrder[]= new int[numOfCards]; //original order
        for (i=0; i<numOfCards; i++) initOrder[i] = i; 
        for (i=0; i<numOfCards; i++){
            r = ran.nextInt(numOfCards-i);
            shuffleOrder[i] = initOrder[r]; //put a random card into i order of the deck
            for (j=r; j<initOrder.length-1; j++) 
                initOrder[j] = initOrder[j+1]; //avoid choose repeatly 
        }
    }
    public static void setTable(){
        for(int i=0; i<4; i++){
            ArrayList<Integer> row = new ArrayList<>();
            row.add(deck.get(count));
            count++;
            PlayGame.table.add(row);
        }
    }
    
}