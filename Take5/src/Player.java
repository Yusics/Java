package take6;
import java.util.*;
import java.lang.*;
import java.io.*;



class Player{
    public int id;
    public String name;
    public int cowHead = 0;
    public ArrayList<Integer> hand = new ArrayList<>();
   
    public int choiceOneCard(Player self){ //幫我看一下self用法是不是錯了
        Random ran = new Random(); 
        int cardNo = ran.nextInt(self.hand.size()); //表示第幾張牌
        int cardId = self.hand.get(cardNo); //表示值
        self.hand.remove(cardNo);
        return cardId;
    }

    public void chooseDeal(Player self){
        int numOfDeck = Dealer.deck.size();
        int numOfHand = self.hand.size(); //表示第幾張牌
        Random ran = new Random(); //之後再加入一點人工智慧
        int cardNo = 0; //假設一開始想要牌組裡的第0張牌
        if (numOfHand%2==0)
            cardNo = ran.nextInt(numOfDeck/2)+numOfDeck/2;
        else
            cardNo = ran.nextInt(numOfDeck/2)+numOfDeck/2;
        int cardId = Dealer.deck.get(cardNo);
        self.hand.add(cardId);
        Dealer.deck.remove(cardNo);
    }
}