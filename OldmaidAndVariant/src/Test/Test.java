import java.util.*;
import java.lang.*;
import java.io.*;
class Data{
	public static int randomint(){
		int r;
		Random ran = new Random();
		r = (int)(ran.nextInt(16)-1);
		return r;
	}
	public static String randomstr(){
		String[] letter = new String[]{"J","Q","K","A","C","D","H","S","R","B","E"};
		Random ran = new Random();
		return letter[ran.nextInt(letter.length)];
	}
	public static String randomcard(){
		int r;
		Random ran = new Random();
		r = (int)(ran.nextInt(54));
		return Card.name(r);
	} 
}
class Card{ //Card is for displaying the suit and the rank of Player's cards.
	public static String suit(int c){
		String s = new String();
		if (c==0) s = "R";// no.0 = R0
		else if (c==1) s = "B";//no.1 = B0
		else if (c%4==3) s = "C";
		else if (c%4==0) s = "D";
		else if (c%4==1) s = "H";
		else if (c%4==2) s = "S";
		return s;
	}
	public static String rank(int c){
		String r = new String();
		int b = (c+6)/4;//NO. 2,3,4,5 = Rank2 and hope b eq 2
		if (c==0) r = "0";
		else if (c==1)  r = "0";
		else if (b==11) r = "J";
		else if (b==12) r = "Q";
		else if (b==13) r = "K";
		else if (b==14) r = "A";
		else            r = Integer.toString(b);
		return r;
	}
	public static String name(int c){
		String n = new String();
		n = suit(c)+rank(c);
		return n;
	}
}
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
class Shuffler{// shuffler shuffles cards randomly
	public static void shuffle(int[] poker){
		int j,temp,r;
		int num = poker.length;
		for (j=0; j<num; j++){  //shuffle
	    	Random ran = new Random();
	    	r    = (int)(ran.nextInt(num-j) +j);
	    	temp = poker[j];
	    	poker[j] = poker[r];
	    	poker[r] = temp;
	    }
	}
}
class Game{
	private static int count= 0;
	private static int cw   = 0;
	public static void basic(Player[] p){// basic game rule
		Computer Com = new Computer();
		while(p[0].hand.size()!=0 && p[1].hand.size()!=0 && p[2].hand.size()!=0 && p[3].hand.size()!=0){
	        p[count%4].drawcard(p[(count+1)%4]);
	        p[count%4].dropcard();
	        Com.showcard(p[count%4]);
	        Com.showcard(p[(count+1)%4]);
	        cw = Com.checkwinner(p[count%4],p[(count+1)%4],cw);
	        count++;
	    }
	}
	public static void bonus(Player[] p){//bonus game rule
	    int i,s;//switch
	    Computer Com = new Computer();
	    while(cw<3){
	    	s=1;
	    	if(p[count%4].hand.size()!= 0){
	    	    i = (count+1)%4;
	    		while(s==1){
	    			if(p[i].hand.size()!= 0){
	    			    p[count%4].drawcard(p[i]);
	    			    p[count%4].dropcard();
	    			    Com.showcard(p[count%4]);
	    			    Com.showcard(p[i]);
	    			    cw = Com.checkwinner(p[count%4],p[i],cw);
	    			    s = 0;
	    			}
	    			i++;
	    			if(i==4) i-=4;
	    		 }
	    	}
	    	count++;
	    }
	}
}
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
class HPlayer extends Player{
	/*public void inputname() throws IOException{
		BufferedReader username = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter your name: ");
		name = username.readLine();
	}*/
	public void drawcard(Player p2){
		int num = p2.hand.size();
		while(true){
			try{
				System.out.println("\nWhich card do you want to draw from "+p2.name+"? Please enter: "+1+"-"+num);
				int no = Data.randomint();
				if(no < 1 || no > num){
					System.out.print("Range of input is from "+1+"-"+num+". Please try agian");
				}
				else{
					String s = Card.name((int)(p2.hand.get(no-1)));
					System.out.print("You draw a card from "+p2.name+" "+s); 
					hand.add(p2.hand.get(no-1));
					p2.hand.remove(no-1);
					break;
				}
			}
			catch(Exception ex){
				System.out.println("Only Integer "+1+"-"+num+" is acceptable");
			}
		}
	}
}
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
class OldmaidVar1 extends OldmaidNormal{
	// remove a card from the deck randomly
	protected int[] new_deck(){
		int i,r;
		ArrayList deck = new ArrayList();
		for(i=2; i<54; i++){
			deck.add(i);
		}
		Random ran = new Random();
	    r    = (int)(ran.nextInt(52)+2);
	    deck.remove(r);// remove one of the card in the deck randomly
	    System.out.println(Card.name(r)+" is removed from the deck.");
	    int[] deckvar1 = new int[51];
		for(i=0; i<deck.size(); i++){
			deckvar1[i]=(int)(deck.get(i));
		}
		Shuffler.shuffle(deckvar1);
		return deckvar1;
	}
}
class OldmaidVar2 extends OldmaidNormal{
	protected int[] new_deck(){
		int i,r;
		ArrayList deck = new ArrayList();
		for(i=2; i<54; i++){
			deck.add(i);
		}
		Random ran = new Random();
	    r    = (int)(ran.nextInt(52)+2);
	    deck.add(r);// add one of the card in the deck randomly
	    System.out.println(Card.name(r)+" is added in the deck.");
	    int[] deckvar2 = new int[53];
		for(i=0; i<deck.size(); i++){
			deckvar2[i]=(int)(deck.get(i));
		}
		Shuffler.shuffle(deckvar2);
		return deckvar2;
	}
}
class OldmaidVar3 extends OldmaidNormal{
	protected Player[] new_player(){
		int i,r;
		Random ran = new Random();
		r = ran.nextInt(4);
		Player[] p = new Player[4];
		for(i=0; i<4; i++){
			if(i==r){
				p[i] = new HPlayer();
				p[i].id = i;
				p[i].name = ("Player"+i);
				System.out.println("Hi! You are "+p[i].name+" now!");
			}
			else{
	    		p[i] = new Player();
	    		p[i].name = ("Player"+i);
	    		p[i].id = i;
	    	}
	    }
	    return p;
	}
} 
class OldmaidVar4 extends OldmaidVar3{
	public static int removedcard;
	protected int[] new_deck(){
		int i;
		ArrayList deck = new ArrayList();
		for(i=2; i<54; i++){
			deck.add(i);
		}
		Random ran  = new Random();
	    removedcard = (int)(ran.nextInt(52)+2);
	    deck.remove(removedcard);// add one of the card in the deck randomly
	    int[] deckvar2 = new int[51];
		for(i=0; i<deck.size(); i++){
			deckvar2[i]=(int)(deck.get(i));
		}
		Shuffler.shuffle(deckvar2);
		return deckvar2;
	}
	public void guess(){
		System.out.println("Guess which card is removed before the game started. InputEx:S3");
		Scanner scan = new Scanner(System.in);
		String g = Data.randomcard();
		if(g.equals(Card.name(removedcard))) System.out.println("Congratulation! You got it!");
		else System.out.println("Oops, the removed card is "+Card.name(removedcard));
	}
}
class OldmaidVar5 extends OldmaidVar3{
	public static int addedcard;
	protected int[] new_deck(){
		int i;
		ArrayList deck = new ArrayList();
		for(i=2; i<54; i++){
			deck.add(i);
		}
		Random ran  = new Random();
	    addedcard = (int)(ran.nextInt(52)+2);
	    deck.add(addedcard);// add one of the card in the deck randomly
	    int[] deckvar2 = new int[53];
		for(i=0; i<deck.size(); i++){
			deckvar2[i]=(int)(deck.get(i));
		}
		Shuffler.shuffle(deckvar2);
		return deckvar2;
	}

	public void guess(){
		System.out.println("Guess which card is added before the game started. InputEx:S3");
		String g = Data.randomcard();
		if(g.equals(Card.name(addedcard))) System.out.println("Congratulation! You got it!");
		else System.out.println("Oops, the added card is "+Card.name(addedcard));
	}
}
class Version{
	public static void version(){
		while(true){
			try{
				System.out.println("What kind of version do you want to play? 0:Normal, 1:var1, 2:var2, 3:var3, 4:var4, 5:var5");
				int v = Data.randomint();
				if(v < 0 || v > 5){
					System.out.println("Range of input is from 0-5. Please try again.");
				}
				else{
					if(v==0){ 
						OldmaidNormal PlayGame = new OldmaidNormal();
						PlayGame.startgame();
						break;
					}
					if(v==1){
						OldmaidVar1 PlayGame = new OldmaidVar1();
						PlayGame.startgame();
						break;
					}
					if(v==2){
						OldmaidVar2 PlayGame = new OldmaidVar2();
						PlayGame.startgame();
						break;
					}
					if(v==3){
						OldmaidVar3 PlayGame = new OldmaidVar3();
						PlayGame.startgame();
						break;
					}
					if(v==4){
						OldmaidVar4 PlayGame = new OldmaidVar4();
						PlayGame.startgame();
						PlayGame.guess();
						break;
					}
					if(v==5){
						OldmaidVar5 PlayGame = new OldmaidVar5();
						PlayGame.startgame();
						PlayGame.guess();
						break;
					}
				}
			}
			catch(Exception ex){
				System.out.println("Only integer 0-3 is acceptable. Please try again.");
			}
		}
	}
}
public class Test{
	public static void main(String[] args){
		while(true){
			Version.version();
		}
	}
}