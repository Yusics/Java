package take6;
import java.util.*;
import java.lang.*;
import java.io.*;

class Computer{ //負責程式的主要計算部分
    public static void setSort(int[] list, int[] listSort){ 
    //將一個放數值的陣列轉換成由小至大排列的陣列
        for(int i=0; i<PlayGame.numOfPlayers; i++){ 
            listSort[i] = list[i];
        }
        Arrays.sort(listSort, 0, PlayGame.numOfPlayers);
    }
    public static void setRow(int[] row, int[] rowSort){ //建立陣列存入檯面上四個列的後一張牌
        for(int j=0; j<4; j++){
            int numOflatest = PlayGame.table.get(j).size(); //找到最後一張牌
            row[j] = (int)PlayGame.table.get(j).get(numOflatest-1); //找到最後一張牌的數值
            rowSort[j] = row[j];
        }
        Arrays.sort(rowSort, 0, 4);
    }
    public static int setPutInRow(int chosenCardSort, int[] rowSort){ //判斷某張牌應該放入哪一列
        int putInRow = -1;
        for(int i=0; i<4; i++){ //這個方法滿聰明
            if (chosenCardSort < rowSort[i]) break;
            else putInRow++;
        }
        return putInRow;
    }
    public static int findRealPlayer(int[] chosenCard, int chosenCardSort){ //找原本的玩家
        for(int i=0; i<PlayGame.numOfPlayers; i++){
            if (chosenCard[i]==chosenCardSort) 
                return i;
        }
        System.out.println("wrong!");
        return -1;
    }
    public static int findRealRow(int[] row, int[] rowSort, int putInRow){ //找原本的列
        for(int i=0; i<4; i++){ 
            if (row[i]==rowSort[putInRow]) return i; 
        }
        System.out.println("wrong!");
        return -1;
    }
    public static void addPlayerHead(int playerId, int rowId){ //計算玩家把某列的牌吃掉後要增加多少牛頭數
        System.out.printf("player%d's head: %d --> ", playerId, PlayGame.playerList[playerId].cowHead);
        int addHead;
        for(int i=0; i<PlayGame.table.get(rowId).size(); i++){ //改head的函式，加很多函式
            addHead = ShowCard.getHead((int)PlayGame.table.get(rowId).get(i));
            PlayGame.playerList[playerId].cowHead += addHead;
        }
        System.out.printf("head: %d.\n", PlayGame.playerList[playerId].cowHead);
    }
    public static int leastHeadRow(){ //找出台面上總牛頭數最少的列
        int head[] = {0, 0, 0, 0};
        for(int i=0; i<4; i++){
            for(int j=0; j<PlayGame.table.get(i).size(); j++)
                head[i] += ShowCard.getHead((int)PlayGame.table.get(i).get(j));
        }
        int row = 0; //找head最少的row回傳
        for(int i=1; i<4; i++){
            if (head[i]<head[row]) row = i;
        }
        return row;
    }
    public static void setRow(int row, int replacedCard){ //用某張牌取代某個列
        PlayGame.table.get(row).clear();
        PlayGame.table.get(row).add(replacedCard);
    }
    public static int[] setHead(){ //建立一個陣列存入所有玩家的牛頭數
        int head[] = new int[PlayGame.numOfPlayers];
        for(int i=0; i<PlayGame.numOfPlayers; i++){
            head[i] = PlayGame.playerList[i].cowHead;
        }
        return head;
    }
}