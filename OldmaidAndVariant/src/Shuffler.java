import java.util.*;
import java.lang.*;
import java.io.*;

class Shuffler{// shuffler shuffles cards randomly
	public static void shuffle(int[] poker){
		int j,temp,r;
		int num = poker.length;
		for (j=0; j<num; j++){  //shuffle
	    	Random ran = new Random();
	    	r    = (int)(ran.nextInt(num-j) +j);
	    	temp = poker[j];
	    	poker[j] = poker[r];
	    	poker[r] = temp;
	    }
	}
}