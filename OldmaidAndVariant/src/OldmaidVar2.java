import java.util.*;
import java.lang.*;
import java.io.*;

class OldmaidVar2 extends OldmaidVar1{
	public static int removedcard;
	protected int[] new_deck(){
		int i;
		ArrayList deck = new ArrayList();
		for(i=2; i<54; i++){
			deck.add(i);
		}
		Random ran  = new Random();
	    removedcard = (int)(ran.nextInt(52)+2);
	    deck.remove(removedcard-2);// add one of the card in the deck randomly
	    int[] deckvar2 = new int[51];
		for(i=0; i<deck.size(); i++){
			deckvar2[i]=(int)(deck.get(i));
		}
		Shuffler.shuffle(deckvar2);
		return deckvar2;
	}
	public void guess(){
		System.out.println("Guess which card is removed before the game started. Input Ex:S3");
		Scanner scan = new Scanner(System.in);
		String g = scan.next();
		if(g.equals(Card.name(removedcard))) System.out.println("Congratulation! You got it!");
		else System.out.println("Oops, the removed card is "+Card.name(removedcard));
	}
}