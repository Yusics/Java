import java.util.*;
import java.lang.*;
import java.io.*;

class Computer{
	public static void deck(int[] cards){
		int i;
		for(i=0; i<cards.length; i++){
			cards[i]=i;
		}
	}
	public void showcard(Player p){//show Player's cards
		int i,c;
		Collections.sort(p.hand);
		System.out.printf("\n%3s:",p.name);
		for(i=0; i<p.hand.size(); i++){
			c = (int)(p.hand.get(i));
		    System.out.printf("%4s",Card.name(c));
		}
	}
	public int checkwinner(Player p1,Player p2,int cw){//cw counts num of winners
		String P1 = p1.name;
		String P2 = p2.name;
		if(p1.hand.size() == 0 && p2.hand.size()==0){
			String temp = new String();
			if(p1.id>p2.id){
				temp = P1;
				P1   = P2;
				P2   = temp;
			}
	        System.out.print("\n"+P1+" and "+P2+" win");
	            cw += 2;}
	    else if(p1.hand.size() == 0){
	        System.out.print("\n"+P1+" wins");
	        	cw += 1;}
	    else if(p2.hand.size() == 0){
	        System.out.print("\n"+P2+" wins");
	        	cw += 1;}
	    else cw += 0;
	    return cw;
	}
	public void deal(int[] deck,Player[] p){
		int i;
		for(i=0; i<deck.length; i++){
			p[i%4].hand.add(deck[i]);
	    }
	}
}