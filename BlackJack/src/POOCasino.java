import foop.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.reflect.Constructor;

//// blackjack bet

public class POOCasino{
	public static boolean game_over = false; 
	public static ArrayList<Hand> current_table = new ArrayList<Hand>();
	public static ArrayList<Hand> last_table = new ArrayList<Hand>();
	public static ArrayList<Card> poker = new ArrayList<Card> ();
	public static ArrayList<Boolean> hasSplitted = new ArrayList<Boolean>();
	public static ArrayList<Integer> bet = new ArrayList<Integer>();
	public static ArrayList<Integer> insurance = new ArrayList<Integer>();
	public static ArrayList<Boolean> blackjack = new ArrayList<Boolean>();
	public static ArrayList<Boolean> bust = new ArrayList<Boolean>();
	public static ArrayList<Boolean> finish = new ArrayList<Boolean>();
	
	// initialize the status of the whole game
	public void initialize(){
		blackjack.clear();
		hasSplitted.clear();
		finish.clear();
		bust.clear();
		bet.clear();
		insurance.clear();
		for(int i =0; i< 9; i++){
			blackjack.add(false);
			hasSplitted.add(false);
			finish.add(false);
			bust.add(false);
			bet.add(0);
			hasSplitted.add(false);
			insurance.add(0);
		}
	}
	// open new deck and shuffle 
	public void new_deck_shuffle(){
		for(int i = 0; i < 52; i++){
			byte suit = (byte)(i % 4 + 1);
			byte value = (byte)(i/4 + 1); 
			Card newCard = new Card(suit, value);
			poker.add(newCard);
		}
	///// Shuffle deck
		int r;
		Card temp;
		for (int j = 0; j < 52; j++){  //shuffle
		    Random ran = new Random();
		    r    = (int)(ran.nextInt(52-j) +j);
		    temp = poker.get(j);
		    poker.set(j, poker.get(r));
		    poker.set(r,temp);
		}
	}

	public void ask_bet(ArrayList<Player> players){
		System.out.println("How much do you want to bet?");
		for(int i=0; i<4 ;i++){
			System.out.print("Player"+(i+1)+": ");
			bet.set(i,players.get(i).make_bet(last_table, players.size(), i));
		}
	}

	// deal first hand to Dealer, and then foru players

	public void deal_card(){
		for(int i = 0; i<9; i++){
			ArrayList<Card> myHand = new ArrayList<Card>();
			Hand myHand2;
			myHand.add(poker.get(0));
			myHand.add(poker.get(1));
			poker.remove(0);
			poker.remove(0);
			myHand2 = new Hand(myHand);
			current_table.add(myHand2);
			if(i==0) {
				System.out.print("Dealer: ");
				show_card(myHand.get(0));
			}
			else if(i<5){
				System.out.print("Player"+i+": ");
				show_card(myHand.get(0));
			}
			else {
				ArrayList<Card> card = new ArrayList<Card>();
 				Hand hand = new Hand(card);
				current_table.add(hand);
			}
		}
	}

	public void deal_splitCard(int i){
		ArrayList<Card> myHand = new ArrayList<Card>();
		Hand myHand2;
		myHand.add(poker.get(0));
		myHand.add(poker.get(1));
		poker.remove(0);
		poker.remove(0);
		myHand2 = new Hand(myHand);
		current_table.set(i+5,myHand2);
	}

	public static void deal_oneCard(int i ){
		ArrayList<Card> myHand = current_table.get(i).getCards();
		Hand myHand2;
		myHand.add(poker.get(0));
		poker.remove(0);
		myHand2 = new Hand(myHand);
		current_table.set(i,myHand2);
	}
	public boolean check_faceUp_Ace(){
		boolean check;
		if (current_table.get(0).getCards().get(0).getValue() == 1) {
			check = true;
			System.out.println("Dealer has an Ace");
		}
		else check = false;
		return check;
	}

	public void ask_insurance(ArrayList<Player> players){
		System.out.println("Do you want to buy insurance?");
		for(int i=0; i<players.size() ;i++){
			if(players.get(i).buy_insurance(current_table.get(i+1).getCards().get(0),current_table.get(0).getCards().get(0), current_table) == true) {
				insurance.set(i,bet.get(i)/2);
				System.out.println("Player"+(i+1)+": Buy insrance");
			}
			else{
				System.out.println("Player"+(i+1)+": No insurance");
			}
		}
	}

	public boolean check_BlackJack(Hand hand){
		if(computeValue(hand) == 21) return true;
		else return false;
	}
	public void ask_surrender(ArrayList<Player> players){
		System.out.println("\nDo you want to surrender?");
		for(int i=0; i<players.size() ;i++){
			if(players.get(i).do_surrender(current_table.get(i+1).getCards().get(0),current_table.get(0).getCards().get(0), current_table) == true) {
				int old_bet = bet.get(i);
				bet.set(i,old_bet/2);
				System.out.println("Player"+(i+1)+": I want surrender");
			}
			else{
				System.out.println("Player"+(i+1)+": No surrender.");
			}
		}
	}

	public void check_playerBlackjack(ArrayList<Player> players){
		for(int i = 0; i< players.size(); i++){
			blackjack.set(i+1,check_BlackJack(current_table.get(i+1))) ;
			if(hasSplitted.get(i+5) == true) blackjack.set(i+5,check_BlackJack(current_table.get(i+5))) ;
		}
	}

	public void show_initial_card(ArrayList<Player> players){
		for(int i = 0; i < players.size(); i++){
			System.out.print("\nPlayer"+(i+1)+": ");
			POOCasino.show_hand(current_table.get(i+1).getCards());
		}
	}

	public void ask_split(ArrayList<Player> players){
		for(int i=0; i<players.size() ;i++){
			if(count_cardValue(current_table.get(i+1).getCards().get(0)) == count_cardValue(current_table.get(i+1).getCards().get(1))){
				System.out.println("\nDo you want to split?");
				if(players.get(i).do_split(current_table.get(i+1).getCards(), current_table.get(0).getCards().get(0), current_table) == true){
					System.out.println("Player"+(i+1)+": I want to split");
					hasSplitted.set(i,true);
					deal_splitCard(i);
				}
				else{
					System.out.println("Player"+(i+1)+": I don't want to split");
				}
			}
		}
	}
	public void ask_double(ArrayList<Player> players, ArrayList<Card> poker){
		System.out.println("\nDo you want to double?");
		for(int i = 0; i< players.size(); i++){
			int old_bet = bet.get(i);
			if(players.get(i).do_double(current_table.get(i+1), current_table.get(0).getCards().get(0), current_table) == true){
				System.out.println("Player"+(i+1)+": Double ");
				finish.set(i+1,true);
				deal_oneCard(i+1);
				bet.set(i,old_bet*2);
			}
			else {
				System.out.println("Player"+(i+1)+": No Double ");
			}
			if(hasSplitted.get(i) == true){
				if(players.get(i).do_double(current_table.get(i+5), current_table.get(0).getCards().get(0), current_table) == true){
					System.out.println("Player"+(i+1)+": splittedHand Double ");
					finish.set(i+5,true);
					bet.set(i+5,old_bet * 2);
					deal_oneCard(i+5);
				}
			}
		}
	}

	public static void check_busted_player(int num){
		int value;
		value = POOCasino.computeValue(current_table.get(num));
		if(value>21) {
			bust.set(num,true);
			System.out.print("Player"+num+": ");
			show_hand(current_table.get(num).getCards());
			System.out.printf("  value: %4s\n",value);
			System.out.println("Player"+num+" is busted.");
			finish.set(num,true);
		}
		if(hasSplitted.get(num) == true){
			value = POOCasino.computeValue(current_table.get(num+4));
			if(value > 21){
				bust.set(num+4,true);
				System.out.print("Player"+(num)+": ");
				show_hand(current_table.get(num+4).getCards());
				System.out.printf("  value: %4s\n",value);
				System.out.println("Player"+num+" splitted hand is busted.");
				finish.set(num+4,true);
			}
		}
	}
	public static void check_busted_dealer(){
		int value;
		value = POOCasino.computeValue(current_table.get(0));
		if(value>21) {
			bust.set(0,true);
			System.out.print("Dealer: ");
			show_hand(current_table.get(0).getCards());
			System.out.println("  value: "+computeValue(current_table.get(0)));
			System.out.println("\nDealer is busted.");
			finish.set(0,true);
			
		}
	}

	public void check_finish(ArrayList<Player> players, ArrayList<Card> poker){
		for(int i = 0; i< players.size(); i++){
			if(finish.get(i+1) == true){
				System.out.print("Player"+(i+1)+": ");
				POOCasino.show_hand(current_table.get(i+1).getCards());
				System.out.println("  value: "+computeValue(current_table.get(i+1)));
			}
			while(finish.get(i+1) == false){
				if(players.get(i).hit_me(current_table.get(i+1),current_table.get(0).getCards().get(0),current_table) == true){
					deal_oneCard(i+1);
					check_busted_player(i+1);
				}
				else{
					System.out.print("Player"+(i+1)+": ");
					POOCasino.show_hand(current_table.get(i+1).getCards());
					System.out.println("  value: "+computeValue(current_table.get(i+1)));
					finish.set(i+1,true);
				}
			}
			if(hasSplitted.get(i) == true){
				while(finish.get(i+5) == false){
					if(players.get(i).hit_me(current_table.get(i+5),current_table.get(0).getCards().get(0),current_table) == true){
						deal_oneCard(i+5);
						POOCasino.check_busted_player(i);
					}
					else{
						System.out.print("Player"+(i+1)+": ");
						POOCasino.show_hand(current_table.get(i+5).getCards());
						System.out.println("  value: "+computeValue(current_table.get(i+5)));
						finish.set(i+5,true);
					}
				}
			}
		}
	}

	public void check_finish_dealer(ArrayList<Card> poker){
		while( finish.get(0) == false ){
			if(POOCasino.computeValue(current_table.get(0)) < 17){
				deal_oneCard(0);
				check_busted_dealer();
			}
			else {
				System.out.print("Dealer: ");
				POOCasino.show_hand(current_table.get(0).getCards());
				System.out.println("  value: "+computeValue(current_table.get(0)));
				finish.set(0,true);
			}
		}
	}
		

	public void check_winner(ArrayList<Player> players){
		for(int i = 0; i < 4; i++){
			if(blackjack.get(0)==true && insurance.get(i)>0){
				try{
					players.get(i).increase_chips((double)insurance.get(i));
					System.out.println("Player"+(i+1)+": Player"+(i+1)+" gets insurance!");
				}
				catch(Player.NegativeException e){
					System.out.println("Negative is not allowed");
				}
			}
			if (bust.get(i+1) == false && bust.get(0) == false) {
				int d = POOCasino.computeValue(current_table.get(0));
				int p = POOCasino.computeValue(current_table.get(i+1));
				if (d > p){ 
					//players[i].get_chips() -= (double)players[i].bet;
					System.out.println("Player"+(i+1)+": Dealer wins!");
					try{
						players.get(i).decrease_chips((double)bet.get(i));
					}
					catch(Player.NegativeException e){
						System.out.println("Negative is not allowed");
					}
					catch(Player.BrokeException e){
						game_over = true;
					} 
				}
				else if(d == p) {
					System.out.println("Player"+(i+1)+": Push");
				}
				else {
					if(blackjack.get(i+1) == true) {
						System.out.println("Player"+(i+1)+": Player"+(i+1)+" wins! BlackJack");
						bet.set(i,bet.get(i)*3/2);
					}
					else System.out.println("Player"+(i+1)+": Player"+(i+1)+" wins!");
					try{
						players.get(i).increase_chips((double)bet.get(i));
					}
					catch(Player.NegativeException e){
						System.out.println("Negative is not allowed");
					}
				}
			}
			else if(bust.get(i+1) == false && bust.get(0) == true){
				try{
					players.get(i).increase_chips((double)bet.get(i));
				}
				catch (Player.NegativeException e){
					System.out.println("Negative is not allowed");
				}
			}
			else{
				System.out.println("Player"+(i+1)+": busted");
				try{
					players.get(i).decrease_chips((double)bet.get(i));
				}
				catch(Player.NegativeException e){
						System.out.println("Negative is not allowed");
				}
				catch(Player.BrokeException e){
					game_over = true;

				} 
			}

			if(hasSplitted.get(i) == true){
				if (bust.get(i+5) == false && bust.get(0) == false) {
					int d = computeValue(current_table.get(0));
					int p = computeValue(current_table.get(i+5));
					if (d > p){ 
						//players[i].get_chips() -= (double)players[i].bet;
						System.out.println("Player"+(i+1)+" splittedHand : Dealer wins!");
						try{
							players.get(i).decrease_chips((double)bet.get(i+5));
						}
						catch(Player.NegativeException e){
							System.out.println("Negative is not allowed");
						}
						catch(Player.BrokeException e){game_over = true;

						} 
					}
					else if(d == p) {
						System.out.println("Player"+(i+1)+" splittedHand : Push");
					}
					else {
						System.out.println("Player"+(i+1)+" splittedHand : Player"+(i+1)+" wins!");
						if(blackjack.get(i+5) == true) System.out.println(" BlackJack");
						try{
							players.get(i).increase_chips((double)bet.get(i+4));
						}
						catch(Player.NegativeException e){
							System.out.println("Negative is not allowed");
						}
					}
				}
				else if(bust.get(i+5) == false && bust.get(0) == true){
					try{
						players.get(i).increase_chips((double)bet.get(i+4));
					}
					catch (Player.NegativeException e){
						System.out.println("Negative is not allowed");
					}
				}	
				else{
					System.out.println("Player"+(i+1)+" splittedHand : busted");
					try{
						players.get(i).decrease_chips(bet.get(i+4));
					}
					catch(Player.NegativeException e){
						System.out.println("Negative is not allowed");
					}
					catch(Player.BrokeException e){
						game_over = true;
					}
				}
			}
		}
	}
	
	public ArrayList<Player> newPlayers(String[] args){
		ArrayList<Player> players = new ArrayList<Player>();  
		for(int i = 0; i < 4; i++){
			int chip = Integer.parseInt(args[1]);
			try{
				players.add((Player)Class.forName(args[i+2]).getConstructor(Integer.TYPE).newInstance(chip));
			}
			catch(Exception e){

			}	
		}
		return players;
	}
	public static int computeValue(Hand hand){
		ArrayList<Card> card = new ArrayList<Card>();
		boolean isAce = false;
		int value;
		int sum = 0;
		card = hand.getCards();
		for(int i = 0; i < card.size(); i++){
			value = (int)card.get(i).getValue();
			if(value == 1) {
				value = 11;
				isAce = true;
			}
			else if(value == 11 || value ==12 || value ==13) value = 10;
			sum += value;
		}
		if (sum > 21 && isAce == true) sum -= 10;
		return sum;
	}

	public static int count_cardValue(Card card){
		int value =(int)card.getValue();
		if(value == 11 || value == 12 || value ==13){
			value = 10;
		}
		return value;
	}

	public static void show_hand(ArrayList<Card> hand){
		Card card;
		int s,v;
		String suit,value;

		for(int i = 0; i< hand.size(); i++){
			s = (int)hand.get(i).getSuit();
			if      (s==1) suit = "C";
			else if (s==2) suit = "D";
			else if (s==3) suit = "H";
			else           suit = "S";

			v = (int)hand.get(i).getValue();
			if      (v==1)  value = "A";
			else if (v==11) value = "J";
			else if (v==12) value = "Q";
			else if (v==13) value = "K";
			else            value = Integer.toString(v);

			System.out.printf("%4s",suit+value);
		}
	}

	public static void show_card(Card card){
		int s,v;
		String suit,value;

		s = (int)card.getSuit();
		if      (s==1) suit = "C";
		else if (s==2) suit = "D";
		else if (s==3) suit = "H";
		else           suit = "S";

		v = (int)card.getValue();
		if      (v==1)  value = "A";
		else if (v==11) value = "J";
		else if (v==12) value = "Q";
		else if (v==13) value = "K";
		else            value = Integer.toString(v);

		System.out.printf("%4s\n",suit+value);

	}

	public void players_status(ArrayList<Player> players){
		for(int i=0;i<players.size();i++){
			System.out.println("Player"+(i+1)+": "+players.get(i).toString());
		}
	}

	public void gameStart(String[] args){
		System.out.println("Welcome to POOCasino. Enjoy BlackJack!");
		int count = 0;
		POOCasino casino = new POOCasino();
		ArrayList<Player> players = newPlayers(args);
		while (count < Integer.parseInt(args[0]) && game_over == false){
			initialize();
			ask_bet(players);
			new_deck_shuffle();
			System.out.println("Deal Cards");
			deal_card();
			if(check_faceUp_Ace() == true) ask_insurance(players);
			if(check_BlackJack (current_table.get(0)) == false) ask_surrender(players);
			show_initial_card(players);
			ask_split(players);
			check_playerBlackjack(players);
			ask_double(players, poker);
			check_finish(players, poker);
			check_finish_dealer( poker);
			check_winner(players);
			players_status(players);
			last_table = current_table;
			current_table.clear();
			count ++ ;
		}
		ArrayList<Integer> player_chip = new ArrayList<Integer>();
		for(int i = 0; i < 4; i++){
			player_chip.add(Integer.parseInt(players.get(i).toString()));
		}
		Collections.sort(player_chip);
		for(int i = 3; i>=0; i--){
			for(int j =0;i<4;j++){
				if(Integer.parseInt(players.get(j).toString()) == player_chip.get(i)){
					System.out.println("rank "+(4-i)+": "+"Player"+(j+1));
					break;
				}
			}
		}

	} 

	public static void main(String[] args) {
		POOCasino casino = new POOCasino();
		casino.gameStart(args);
	}


}