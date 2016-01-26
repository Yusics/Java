package take6;
import java.util.*;
import java.lang.*;
import java.io.*;

//Diificulty: Hard
class CPlayer2 extends Player{
    public int choiceOneCard(Player self){
        Random ran = new Random(); 
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
        if (sizeSort[0] == sizeSort[1]){ //當有兩列的牌數都最少且相同時，隨機選一列放入
            int r = ran.nextInt(2);
            for(int i=0; i<4; i++){ 
                if (size[i] == sizeSort[putInRow]){
                    if (r==0){
                        putInRow = i;
                        break;
                    }
                    else r=0;
                }
            }    
        }
        else{
            for(int i=0; i<4; i++){ //找原本的列
                if (size[i] == sizeSort[putInRow]){
                    putInRow = i;
                    break;
                }
            }
        }
        int rowValue = row[putInRow]; //找比這個值還要大的牌
        int cardNo = -1; //表示第幾張牌
        for(int i=0; i<self.hand.size(); i++){
            cardNo++;
            if(self.hand.get(i)>rowValue){
                break;
            }
        }
        if (cardNo < 0 || cardNo >= self.hand.size()) cardNo = self.hand.size()-1;
        //沒牌出時就出最大張的牌
        int cardId = self.hand.get(cardNo); //表示值
        self.hand.remove(cardNo);
        return cardId;
    }
}