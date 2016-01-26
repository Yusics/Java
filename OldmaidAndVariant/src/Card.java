import java.util.*;
import java.lang.*;
import java.io.*;

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