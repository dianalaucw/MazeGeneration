package demo.domain;

/**
* implmenting Wilson's algorithm
*/

import java.util.*;

public class Wilson {
	public static void main(String[] args){
		//dimensions
		int r = 10, c = 10;
		
		//build grid of walls
		StringBuilder s = new StringBuilder(c);
		for(int x = 0;x<c;x++){
			s.append('*');
		}
		char[][] maz = new char[r][c];
		for (int x =0;x<r;x++) maz[x] = s.toString().toCharArray();
		
		//Randomly select a cell as starting point and add the wall to wall list
		Point st = new Point((int)(Math.random() * r), (int)(Math.random()*c),null);
		maz[st.r][st.c] = 'S';

	}
	private void walk(){

	}
}
