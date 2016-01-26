import java.util.*;
import java.lang.*;
import java.io.*;

class Version{
	public static void version(){
		while(true){
			try{
				System.out.println("What kind of version do you want to play? 0:Normal version, 1:Normal human version, 2:one card removed human version");
				Scanner scan = new Scanner(System.in);
				int v = scan.nextInt();
				if(v < 0 || v > 2){
					System.out.println("Range of input is from 0-2. Please try again.");
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
						PlayGame.guess();
						break;
					}
				}
			}
			catch(Exception ex){
				System.out.println("Only integer 0-2 is acceptable. Please try again.");
			}
		}
	}
}