import java.util.*;
import java.lang.*;
import java.io.*;

class Computer{
	private int i,c,b,j,count=0,h,payoff=0;
	private boolean straight = false;
	private Card show = new Card();
	public void deck(int[] cards){
		for(i=0;i<52;i++){
			cards[i]=i;
		}
	}
	public void showcard(ArrayList p){
		String[] no= new String[]{" (a)"," (b)"," (c)"," (d)"," (e)"};
		for(i=0;i<5;i++){
			c = (int)(p.get(i));
			String s = show.suit(c);
			String r = show.rank(c);
			System.out.printf("%s%4s ",no[i],s+r);
		}
	}
	public void shownewcard(ArrayList p){
		for(i=0;i<5;i++){
			c = (int)(p.get(i));
			String s = show.suit(c);
			String r = show.rank(c);
			System.out.printf("%4s",s+r);
		}
	}
	public int allin(int money,int bet){
		while(true){
			System.out.println("Do you want to all in ? y/n");
			Scanner scan = new Scanner(System.in);
			String answer = scan.next();
			if(answer.equals("y")) {
				System.out.println("Good Luck!");
				return money;
			}
			else if(answer.equals("n")){
				System.out.println("You are not brave enough.");
				return bet;
			}
			else {
				System.out.println("Please type y or n");
			}
		}
	}
	public String keepcard() throws IOException{
		System.out.println("Which cards do you want to keep?");
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	public int hand(int bet,ArrayList p,int win){
		count = 0;
		payoff = 0;
		Card show = new Card();
		int[] s = new int[4];
		int[] r = new int[15];
		int max;
		if(win>=3) max =500;
		else max = 5;
		for(i=0;i<5;i++){
			c = (int)(p.get(i));
            b = c/4 +2;
            r[b]+=1;
            if(i==4&&r[b]>=6&&(r[b-4]==1&&r[b-3]==1&&r[b-2]==1&&r[b-1]==1)) straight = true;
			switch(show.suit(c)){
				case "C":
				    s[0]+=1;
				    break;
				case "D":
				    s[1]+=1;
				    break;
				case "H":
				    s[2]+=1;
				    break;
				case "S":
				    s[3]+=1;
				    break;
			}
			for(j=i+1;j<5;j++){
				h = ((int)(p.get(j)))/4 +2;
				if(b==h) count++;
			}
		}
		if(count>0){
			switch(count){
				case 1://jack or better pairs
				    if(r[11]==2||r[12]==2||r[13]==2||r[14]==2){ 
				    	System.out.print("Jacks or better");
				    	payoff = bet*1 ;
				    }
				    else {
				    	System.out.print("a non-paying pair");
				    	payoff = 0;
				    }
				    break;
				case 2://two pairs
				    System.out.print("two pairs");
				    payoff = bet*2;
				    break;
				case 3://three of a kind
				    System.out.print("three of a kind");
				    payoff = bet*3;
				    break;
				case 4://full house
				    System.out.print("full house");
				    payoff = bet*9;
				    break;
				case 6://four of a kind
				    System.out.print("four of a kind");
				    payoff = bet*25;
				    break;    
			}
		}
		else{
			if(s[0]==5 || s[1]==5 || s[2]==5 || s[3]==5){
				if(r[11]==1 && r[12]==1 && r[13]==1 && r[14]==1){
					System.out.print("royal flush");
					if (bet!=max) payoff = bet*250;
					else payoff =  bet*800;
				}
				else {
					if(straight==true){ 
						System.out.print("straight flush");
						payoff = bet*50;
					}
					else if(r[14]==1 && r[2]==1 && r[3]==1 && r[4]==1 && r[5]==1){ 
						System.out.print("straight flush");
						payoff = bet*50;
					}
					else{
					    System.out.print("flush"); 
						payoff =  bet*6;
					}
				}
			}
			else{
				if(straight==true){ 
					System.out.print("straight");
					payoff = bet*4;
				}
				else if(r[14]==1 && r[2]==1 && r[3]==1 && r[4]==1 && r[5]==1){ 
					System.out.print("straight");
					payoff = bet*4; 
				}
				else{ 
					System.out.print(show.name((int)p.get(4))+" high");
					payoff =  0;
				}
			}
		}
		return payoff;
	}
	public int wintimes(int payoff,int win,int bet){
		if (payoff==0) {
			System.out.println("You lost "+bet+"$. Give another try!");
			win = 0;
		}
	    else {
	        System.out.println("You win "+payoff+"$.");
	        win ++;
	    }
	    return win;
	}
}