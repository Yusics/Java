import java.util.*;
import java.lang.*;
import java.io.*;

class OldmaidVar1 extends OldmaidNormal{
	protected Player[] new_player(){
		int i,r;
		Random ran = new Random();
		r = ran.nextInt(4);
		Player[] p = new Player[4];
		for(i=0; i<4; i++){
			if(i==r){
				p[i] = new HPlayer();
				p[i].id = i;
				p[i].name = ("Player"+i);
				System.out.println("Hi! You are "+p[i].name+" now!");
			}
			else{
	    		p[i] = new Player();
	    		p[i].name = ("Player"+i);
	    		p[i].id = i;
	    	}
	    }
	    return p;
	}
} 