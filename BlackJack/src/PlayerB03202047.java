import foop.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.reflect.Constructor;


class PlayerB03202047 extends Player{
	public PlayerB03202047 (int chips){
		super(chips);
	}
	public boolean buy_insurance(Card my_open, Card dealer_open, ArrayList current_table){
		if(dealer_open.getValue() == 1){
			if(my_open.getValue() > 7) return true;
			else return false;
		}
		else return false;
		
	}
	
	public boolean do_double(Hand my_open, Card dealer_open, ArrayList<Hand> current_table){
		ArrayList<Card> card = my_open.getCards();
		boolean isAce = false;
		int value;
		int sum = 0;
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
		if(sum >9 && sum < 14) return true;
		else return false;
	} 
	public boolean do_split(ArrayList<Card> my_open, Card dealer_open, ArrayList<Hand> current_table){
		int v1 = my_open.get(0).getValue();
		if(v1 == 2 || v1 == 3 || v1 == 4 || v1 == 5 || v1 == 6 || v1 == 7 || v1 == 8) return true;
		else return false;
		
	}
	public boolean do_surrender(Card my_open, Card dealer_open, ArrayList<Hand> current_table){
		return false;
	}
	public boolean hit_me(Hand my_open, Card dealer_open, ArrayList<Hand> current_table){
		ArrayList<Card> card = my_open.getCards();
		boolean isAce = false;
		int value;
		int sum = 0;
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
		if (sum <= 16){
			return true;
		}
		else return false;

	}
	public int make_bet(ArrayList<Hand> last_table, int total_player, int my_position){
		int bet = (int)get_chips();
		bet = bet /5;
		System.out.println("I want to bet "+bet);
		return bet;
	}
	public String toString(){
		int chip = (int)get_chips();
        return String.valueOf(chip);
	}
}