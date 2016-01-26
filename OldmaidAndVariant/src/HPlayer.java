import java.util.*;
import java.lang.*;
import java.io.*;

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
				Scanner scan = new Scanner(System.in);
				int no = scan.nextInt();
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