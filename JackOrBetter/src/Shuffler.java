import java.util.*;
import java.lang.*;
import java.io.*;
class Shuffler{// shuffler shuffles cards randomly
	private int i,l,r;
	public void shuffle(int poker[]){
		for(i=0;i<51;i++){
	    Random ran = new Random();
	    	r = (int)(ran.nextInt(52-i)+i);
	    	l = poker[i];
	    	poker[i] = poker[r];
	    	poker[r] = l;
		}
    }
}