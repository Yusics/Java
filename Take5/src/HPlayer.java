package take6;
import java.util.*;
import java.lang.*;
import java.io.*;

//人類玩家
class HPlayer extends Player{
    public void chooseDeal(Player self){
        int numOfCDeck = Dealer.deck.size();
        int cardId = PlayGame.variantChoose; //決定是哪一張牌		
        self.hand.add(cardId);
        for(int i =0;i<Dealer.deck.size();i++){
            if(Dealer.deck.get(i) == cardId) Dealer.deck.remove(i);
        }
    }
}