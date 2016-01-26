import java.util.*;
import java.lang.*;
import java.io.*;

public class Jacks
{
	public static void main (String[] args) throws IOException
	{
		int round=1;
		int payoff = 0;
		int win=0;
		int newbet;
		Player Player1 = new Player();
		Computer Com = new Computer();
	    Shuffler Worker = new Shuffler();
	    Casino.start();
	    Player1.name=Casino.welcome();
	    Player1.money  = 1000;
	    String c = new String();
	    while (Player1.money>0){
	        int pb = Casino.deal(round,Player1.money,win);
	        if(pb==0){ 
	        	System.out.println("See you next time, "+Player1.name+".");
	        	break;
	        }
	        Player1.bet = pb;
	        Player1.money -= Player1.bet;
	        int[] new_deck = new int[52];
	        Com.deck(new_deck);
	        Worker.shuffle(new_deck);
	        Player1.drawcards(Player1.cards,new_deck);
	        System.out.print("Your cards are");
	        Com.showcard(Player1.cards);
	        System.out.print("\nYou have ");
	        Com.hand(0,Player1.cards,win);
	        System.out.println(" before discarding cards.");
	        newbet = Com.allin(Player1.money,Player1.bet);
	        if(newbet!=Player1.bet){
	        	Player1.money-=newbet;
	        	Player1.bet += newbet; 
	        }
	        c = Com.keepcard();
	        Player1.keepcard(Player1.cards,new_deck,c);
	        System.out.print("\nYour new cards are");
	        Com.shownewcard(Player1.cards);
	        System.out.print("\nYou have ");
	        payoff = Com.hand(Player1.bet,Player1.cards,win);
	        Player1.money += payoff;
	        System.out.println("\nMoney:"+Player1.money);
	        win = Com.wintimes(payoff,win,Player1.bet);
	        Casino.gameover(Player1.money,Player1.name);
	        Player1.cards.clear();
	        round++;
	    }
	}
}