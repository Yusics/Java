import java.util.*;
import java.lang.*;
import java.io.*;
class Card{//Card is for displaying the suit and the rank of Player's cards.
		private int i,a,b;
		private String s = new String();
		private String n = new String();
		public String suit(int c){
			a = c%4;
			if (a==1) s = "C";
		    if (a==2) s = "D";
		    if (a==3) s = "H";
		    if (a==0) s = "S";
		    return s;
		}
		public String rank(int c){

			b = c/4 +2;//if c/4==0 => its rank is 2,so add 2 here
			if (b==11) n = "J";
		    else if (b==12) n = "Q"; 
		    else if (b==13) n = "K";
		    else if (b==14) n = "A";
		    else       n = Integer.toString(b);
			return n;
		}
		public String name(int c){
			String[] n = new String[]{"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
			b = c/4;
			return n[b];
		}
	}