import java.util.*;
import java.lang.*;
import java.io.*;
class Player{
	private int i;
	private int count ;// count which cards to keep
	public String name;
	public int bet;
	public ArrayList cards = new ArrayList();
	public int money;
	public void drawcards(ArrayList p,int[] deck){
			for(i=0;i<5;i++){
				p.add(deck[i]);
			}
			Collections.sort(p);
		}
	public void keepcard(ArrayList p,int[] deck,String c){
		count = 0;
		char[] no = "abcde".toCharArray();
		ArrayList cardf = new ArrayList();
		char[] keep= c.toCharArray();
		System.out.print("OK I will discard ");
		Card show = new Card();
		if(keep.length==5) System.out.println("none");
		else{
		    for(i=0;i<5;i++){
		    	String s = show.suit((int)(p.get(i)));
		        String r = show.rank((int)(p.get(i)));
		    	if(count<keep.length){
			        if(no[i]==keep[count]){
			            cardf.add(p.get(i));
			            count++;
			        }
			        else System.out.printf("(%c)%4s ",no[i],s+r);
			    }
			    else System.out.printf("(%c)%4s ",no[i],s+r);
		    }
	    }
		p.clear();
		p.addAll(cardf);
		cardf.clear();
		if(count<5){
			for(i=1;i<=(5-count);i++){
				p.add(deck[4+i]);
			}
		}
		Collections.sort(p);
	}
}