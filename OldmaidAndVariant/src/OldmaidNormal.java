import java.util.*;
import java.lang.*;
import java.io.*;

class OldmaidNormal{
	protected int[] new_deck(){
		int[] deck = new int[54];
		Computer.deck(deck);
		Shuffler.shuffle(deck);
		return deck;
	}
	protected Player[] new_player(){
		int i;
		Player[] p = new Player[4];
		String[] player_name = new String[]{"Player0","Player1","Player2","Player3"};
		for(i=0; i<4; i++){
	    	p[i] = new Player();
	    	p[i].name = player_name[i];
	    	p[i].id = i;
	    }
	    return p;
	}
	public void startgame(){
		int i;// cw equals the num of winners
		Player[] p = new_player();
	    Computer Com = new Computer();
	    int[] poker = new_deck();
	    Com.deal(poker,p);
	    System.out.print("Deal cards");
	    for(i=0; i<4; i++){
	        Com.showcard(p[i]);
	    }
	    System.out.print("\nDrop cards");
	    for(i=0; i<4; i++){
	        p[i].dropcard();
	        Com.showcard(p[i]);
	    }
	    System.out.print("\nGame Start");
	    Game.basic(p);
	    System.out.print("\nBasic game over");
	    Game.bonus(p);
	    System.out.println("\nBonus game over");
	}

}