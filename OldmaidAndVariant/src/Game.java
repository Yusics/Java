import java.util.*;
import java.lang.*;
import java.io.*;

class Game{
	private static int count= 0;
	private static int cw   = 0;
	public static void basic(Player[] p){// basic game rule
		Computer Com = new Computer();
		while(p[0].hand.size()!=0 && p[1].hand.size()!=0 && p[2].hand.size()!=0 && p[3].hand.size()!=0){
	        p[count%4].drawcard(p[(count+1)%4]);
	        p[count%4].dropcard();
	        Com.showcard(p[count%4]);
	        Com.showcard(p[(count+1)%4]);
	        cw = Com.checkwinner(p[count%4],p[(count+1)%4],cw);
	        count++;
	    }
	}
	public static void bonus(Player[] p){//bonus game rule
	    int i,s;//switch
	    Computer Com = new Computer();
	    while(cw<3){
	    	s=1;
	    	if(p[count%4].hand.size()!= 0){
	    	    i = (count+1)%4;
	    		while(s==1){
	    			if(p[i].hand.size()!= 0){
	    			    p[count%4].drawcard(p[i]);
	    			    p[count%4].dropcard();
	    			    Com.showcard(p[count%4]);
	    			    Com.showcard(p[i]);
	    			    cw = Com.checkwinner(p[count%4],p[i],cw);
	    			    s = 0;
	    			}
	    			i++;
	    			if(i==4) i-=4;
	    		 }
	    	}
	    	count++;
	    }
	}
}