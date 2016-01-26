import java.util.*;
import java.lang.*;
import java.io.*;

class Player{
	private int i,c1,c2,k;
	public String name;//Player's name
	public ArrayList hand = new ArrayList();//Player's hand
	public int id;// id of the Player
	public void dropcard(){//drop Player's card
		ArrayList cardf = new ArrayList();
		Collections.sort(hand);
		// we check whether i_th card is as same as the i+1_th card,hence no need to check the last card
			for(i=0; i<hand.size()-1; i++){
				c1=(int)(hand.get(i));
				c2=(int)(hand.get(i+1));
				if(Card.rank(c1).equals("0")) continue;
				else if(Card.rank(c1).equals(Card.rank(c2))){
					//cards should be dropped will equal "d" 
					hand.set(i,"d");
					hand.set(i+1,"d");
					i ++;	
				}
			}
			for(i=0;i<hand.size();i++){
				if (hand.get(i).toString()!="d"){ 
					cardf.add(hand.get(i));
				}
			}
			hand.clear();
			hand.addAll(cardf);
	}
	public void drawcard(Player p2){	// draw the card from p2,p1 gets it
		int r;
		Random ran = new Random();
	    r = (int)(ran.nextInt(p2.hand.size()));
	    String s = Card.name((int)(p2.hand.get(r)));
	    System.out.print("\n"+name+" draws a card from "+p2.name+" "+s);
	    hand.add(p2.hand.get(r));
	    p2.hand.remove(r);
	}
}