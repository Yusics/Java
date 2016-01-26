import java.util.*;
import java.lang.*;
import java.io.*;
class Casino{
	public static void start(){
		System.out.println("POOCasino Jacks or better");
	}
	public static String welcome() throws IOException{
		BufferedReader username = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter your name: ");
		String name = username.readLine();
		System.out.println("Welcome, "+name+".\nYou have 1000 P-dollars now.");
		return name;
	}
	public static int deal(int round,int money,int win){
		int bet,min,max;
		if(win>=3){
			min = 100;
			max = 500;
		}
		else{
			min = 1;
			max = 5;
		}
		System.out.print("Please enter your P-dollar bet for round " +round+ " ("+min+"-"+max+" or 0 for quitting the game): ");
		while(true){
			try{// prohibt Player type the wrong bet
				Scanner scan = new Scanner(System.in);
				bet = scan.nextInt();// range of bet is from 1*5*round to 5*5*round (
				if((bet>=min && bet<=max)||(bet==0)) {
					return bet;
				}
				else if(bet>money) System.out.println("Hey! You don't have that much money.");
			    else {
			    	System.out.println("Warning! Please type "+min+"-"+max+" or 0 for quitting the game:");
			    }
			}
			catch (Exception ex){
	    		System.out.println("Only Integer 0 and "+min+"-"+max+" is acceptable");
			}
	    } 
	}
	public static void gameover(int money,String name){
		if(money==0) System.out.println("Poor "+name+". You lost all your money. Good luck next Time!");
	}
}