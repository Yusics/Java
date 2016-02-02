
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import javax.swing.border.*;

public class PlayGame {
	public static JPanel computerPanel;
	public static JPanel playerCardPanel;
	public static JPanel countHeadPanel;
	public static JPanel tablePanel;
	public static JPanel playerCard;
    public static JPanel variantPanel;
	public static int numOfPlayers = 4;
    public static int turn = 0;
    public static int countTurn;
    public static int variantChoose;
    public static int playerChoose;
    public static Player playerList[];
    public static String[] allPlayerName;
    public static int chosenCard[] = new int[numOfPlayers];
    public static ArrayList<JButton> variantButton;
    public static ArrayList<JPanel> rowPanel;
    public static ArrayList<JLabel> playerLabel; 
    public static ArrayList<JPanel> playerPanel;
    public static ArrayList<JButton> cardLabel;
    public static ArrayList<JLabel> headLabel;
    public static ArrayList<JLabel> rowLabel;
    public static ArrayList<JPanel> cardPanel;
    public static ArrayList<ArrayList> table;
    public static boolean chooseTrue = false;
    public static int level;
    public static boolean over = false;
    

	public PlayGame(){
        
		init_All();
        JLabel labelname;
        JLabel cowHead;
        Color color = new Color(243,150,3);
		for(int i = 0; i < 4; i++){
            labelname = new JLabel(allPlayerName[i]);
            cowHead   = new JLabel(allPlayerName[i]+" Heads: 0");
            JPanel namePanel = new JPanel();
            JPanel playerHeadPanel = new JPanel();
            JLabel card = new JLabel();
            Color colorHead = new Color(250,250,228);
            JPanel c1 = new JPanel();
            JPanel c2 = new JPanel();
            c1.setBackground(color);
            c2.setBackground(color);
            playerHeadPanel.add(cowHead);
            playerHeadPanel.setBackground(colorHead);
            headLabel.add(cowHead);
            countHeadPanel.add(playerHeadPanel);
            countHeadPanel.setBackground(colorHead);
            namePanel.setLayout(new GridLayout(2,1,5,5));
            c1.add(labelname);
            c2.add(card);
			namePanel.add(c1);
            namePanel.add(c2);
            playerLabel.add(card);
            namePanel.setBackground(color);
            playerPanel.add(namePanel);
            computerPanel.add(namePanel);
		}
        Start.startFrame.getContentPane().add(BorderLayout.CENTER,tablePanel);
		Start.startFrame.getContentPane().add(BorderLayout.NORTH,computerPanel);
		Start.startFrame.getContentPane().add(BorderLayout.EAST,countHeadPanel);
		Start.startFrame.getContentPane().add(BorderLayout.SOUTH,playerCard);
        nimmt();
	} 

    public static void init_tablePanel(){
        PlayGame.tablePanel.removeAll();
        PlayGame.tablePanel.setLayout(new GridLayout(4,1,30,30));
        Color color = new Color(44,62,80);
        Color colorb = new Color(155,210,217);
        tablePanel.setBackground(colorb);
        for(int i = 0; i < 4; i++){
            JPanel row = new JPanel();
            JPanel r = new JPanel();
            row.setLayout(new GridLayout(1,5,5,5));
            row.setBackground(color);
            tablePanel.add(row);
            rowPanel.add(row);
            for(int j = 0; j < 5; j++){
                JPanel panel = new JPanel();
                panel.setBackground(color);
                JLabel label = new JLabel();
                panel.add(label,BorderLayout.CENTER);
                rowLabel.add(label);
                rowPanel.get(i).add(panel);
            }
        }
    }

    public void init_playPanel(){
        computerPanel = new JPanel();
        playerCardPanel = new JPanel();
        countHeadPanel = new JPanel();
        tablePanel = new JPanel();
        playerCard = new JPanel();
        computerPanel.setLayout(new GridLayout(1,4,5,5));
        computerPanel.setBackground(Color.BLACK);
        playerCard.setLayout(new GridLayout(1,10,5,5));
        countHeadPanel.setLayout(new GridLayout(4,1,10,10));
    }


    public void init_playPanelList(){
        variantButton = new ArrayList<JButton>();
        rowPanel = new ArrayList<JPanel>();
        playerLabel = new ArrayList<JLabel>();
        playerPanel = new ArrayList<JPanel>();
        cardLabel = new ArrayList<JButton>();
        headLabel = new ArrayList<JLabel>();
        rowLabel = new ArrayList<JLabel>();
        cardPanel = new ArrayList<JPanel>();
        table = new ArrayList<>();
    }

    public void init_Card(){
        for(int i = 0; i < 10; i++){
            JPanel card = new JPanel();
            JButton button = new JButton();
            cardLabel.add(button);
            card.add(button);
            Color colorCard = new Color(155,210,217);
            card.setBackground(colorCard);
            cardPanel.add(card);
            playerCard.add(card);
        }
        Color colorCard = new Color(155,210,217);
        playerCard.setBackground(colorCard);
    }

    public void init_All(){
        countTurn = 0;
        allPlayerName = new String[]{OneName.player1name,OneName.com1name,OneName.com2name,OneName.com3name};
        Start.startFrame.setSize(768,1024);
        Start.startFrame.setVisible(true);
        Start.startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init_playPanelList();
        init_playPanel();

        init_Card();
    }

    public static void addListener(){
        for(int i = 0; i < 10; i++){
            cardLabel.get(i).addActionListener(new cardButtonListener());
        }
    }

	public void nimmt(){
        setPlayer();
        playOneRound();     
    }

    public void setPlayer(){
        playerList = new Player[numOfPlayers];
        playerList[0] = new HPlayer();
        for (int i=1; i<numOfPlayers; i++){
            if (level == 0)
                playerList[i] = new Player();
            else if (level == 1)
                playerList[i] = new CPlayer1();
            else
                playerList[i] = new CPlayer2();
        }
        for (int i=0; i<numOfPlayers; i++){
            playerList[i].id = i;
            playerList[i].name = Integer.toString(i)+Integer.toString(i);
        }    
    }


    public void playOneRound(){
        if (Mode.mode.equals("Normal")) Dealer.dealCard(); 
        else                            Dealer.dealVariant();

    }

    public static void putCard(){
        askChosenCard(); //問每個玩家要出哪張牌
        putCardIn(); //把排放進正確的列中，並且計算牛頭
        for(int i=0; i<PlayGame.table.size(); i++){
            String s = Integer.toString(playerList[i].cowHead);
            headLabel.get(i).setText(allPlayerName[i]+" Heads: "+s);
        }
        ShowCard.showAllCards();
        turn++;
        if(over == true){
            ArrayList<String> playerRank = new ArrayList<String>();
            int head[] = Computer.setHead(); //計算每個人的總牛頭數
            int headSort[] = new int[4];
            Computer.setSort(head, headSort);
            for(int i=0; i<PlayGame.numOfPlayers; i++){
                for(int j=0; j<PlayGame.numOfPlayers; j++){
                    if (head[j]==headSort[i]){
                        playerRank.add(PlayGame.allPlayerName[j]);
                    }
                }
            }
            int result = JOptionPane.showConfirmDialog(Start.startFrame,"Game Over. \nThe rank is\nRank1: "+playerRank.get(0)+"\nRank2: "+playerRank.get(1)+"\nRank3: "+playerRank.get(2)+"\nLoser: "+playerRank.get(3)+"\nPlay Again?");
            if(result==JOptionPane.YES_OPTION){
                Start.startFrame.getContentPane().removeAll();
                Start.startFrame.setVisible(false);
                PlayGame new_game = new PlayGame();
            }
            else if(result == JOptionPane.NO_OPTION){
                Start.startFrame.getContentPane().removeAll();
                Start newStart = new Start();
                newStart.startGame();
            }
        }
    }

    public static int[] askChosenCard(){
        for(int i=0; i<numOfPlayers; i++){
            if(i == 0) {
                chosenCard[0] = playerChoose;
            }
            else {
                chosenCard[i] = playerList[i].choiceOneCard(playerList[i]); //ask player which card to choose
                ImageIcon card = Dealer.setImage(chosenCard[i]+1) ;
                playerLabel.get(i).setIcon(card);
            }
        }
        return chosenCard;
    }

    public static void putCardIn(){
        int chosenCardSort[] = new int[numOfPlayers];
        Computer.setSort(chosenCard, chosenCardSort);

        for(int i=0; i<PlayGame.numOfPlayers; i++){
            int row[] = new int[4]; //存入四個列中最後一張牌的值
            int rowSort[] = new int[4];
            Computer.setRow(row, rowSort);
            int putInRow = Computer.setPutInRow(chosenCardSort[i], rowSort); //牌要放進這一列
            if (putInRow == -1) cardTooSmall(chosenCardSort[i], chosenCard);
            //放入的牌比任何一列都還要小
            else{
                putInRow = Computer.findRealRow(row, rowSort, putInRow);
                if (PlayGame.table.get(putInRow).size() < 5){
                    ArrayList<Integer> newRow = PlayGame.table.get(putInRow);
                    newRow.add(chosenCardSort[i]);
                    PlayGame.table.set(putInRow, newRow);
                }
                else rowMoreThan5(chosenCard, chosenCardSort[i], putInRow);
            }
        }

    }

    public static void cardTooSmall(int chosenCardSort, int[] chosenCard){
        int rowId = Computer.leastHeadRow(); //重新放的列
        int playerId = Computer.findRealPlayer(chosenCard, chosenCardSort);
        Computer.addPlayerHead(playerId, rowId);
        Computer.setRow(rowId, chosenCardSort); 
        //將那張小排放進點數最少、編號最小的列（非值最小）
    }
    public static void rowMoreThan5(int[] chosenCard, int chosenCardSort, int putInRow){
        ShowCard.showOneRowCard(putInRow);
        int playerId = Computer.findRealPlayer(chosenCard, chosenCardSort);
        Computer.addPlayerHead(playerId, putInRow);
        Computer.setRow(putInRow, chosenCardSort);
    }

    public static void resetGame(){
        for(int i=0; i<PlayGame.numOfPlayers; i++){
            PlayGame.playerList[i].hand.clear();
            PlayGame.playerList[i].cowHead = 0;
        }
        PlayGame.table.clear();
    }
}
