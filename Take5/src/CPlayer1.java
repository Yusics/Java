
import java.util.*;
import java.lang.*;
import java.io.*;

//medium程度的電腦玩家
class CPlayer1 extends Player{
    public int choiceOneCard(Player self){
        int row[] = new int[4]; //存入四個列中最後一張牌的值
        int rowSort[] = new int[4];
        Computer.setRow(row, rowSort);
        int size[] = new int[4];
        int sizeSort[] = new int[4];
        for(int i=0; i<4; i++){
            size[i] = PlayGame.table.get(i).size(); 
            sizeSort[i] = size[i];
        }
        Arrays.sort(sizeSort, 0, sizeSort.length-1);
        int putInRow = 0;
        for(int i=0; i<4; i++){ //找出檯面上最少張的那列放入
            if (size[i]==sizeSort[putInRow]){
                putInRow = i; 
                break;
            }
        }
        int rowValue = row[putInRow]; //找比這個值還要大的牌
        int cardNo = -1; //表示第幾張牌
        for(int i=0; i<self.hand.size(); i++){
            cardNo++;
            if(self.hand.get(i)>rowValue) break;
        }
        if (cardNo < 0 || cardNo >= self.hand.size()) cardNo = self.hand.size()-1;
        int cardId = self.hand.get(cardNo); //表示要出的牌的值
        self.hand.remove(cardNo);
        return cardId;
    }
}