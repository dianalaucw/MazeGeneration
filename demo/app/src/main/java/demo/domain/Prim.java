package demo.domain;

/**
* implmenting Prim's algorithm
*/

import java.util.*;

public class Prim {


	public static void main(String[] args){
	
		//dimensions
		int r = 10, c = 10;
		
		//build grid of walls
		StringBuilder s = new StringBuilder(c);
		for(int x = 0;x<c;x++){
			s.append('X');
		}
		char[][] maz = new char[r][c];
		for (int x =0;x<r;x++) maz[x] = s.toString().toCharArray();
		
		//Randomly select a cell as starting point and add the wall to wall list
		Point st = new Point((int)(Math.random() * r), (int)(Math.random()*c),null);
		maz[st.r][st.c] = 'S';
		//all the neighbors of the node is its surrounding walls
		ArrayList<Point> frontier = new ArrayList<Point> ();
		for(int x =-1;x<=1;x++){
			for(int y = -1;y<=1;y++){
				if(x==0&& y==0||x!=0 && y!=0){
					continue;
				}
				try{
					if(maz[st.r+x][st.c+y] =='.') continue;
				} catch(Exception e) {
						continue;
					}
				//add all wall that has not been visited to frontier list
				frontier.add(new Point(st.r+x,st.c+y,st));
			}
		}
		
		Point last = null;
		
		while(!frontier.isEmpty()){
			
			//pick a random wall if cells is visited
			Point cu = frontier.remove((int)(Math.random()*frontier.size()));
			Point op = cu.opposite();
			try{
				if(maz[cu.r][cu.c] =='X'){
					if(maz[op.r][op.c]=='X'){
						maz[cu.r][cu.c] ='.';
						maz[op.r][op.c] ='.';
						
						last = op;
						//iterate through the neighbor of opposite node
						for(int x =-1;x<=1;x++){
							for(int y = -1;y<=1;y++){
								if(x==0&& y==0||x!=0 && y!=0){
									continue;
								}
								try{
									if(maz[op.r+x][op.c+y] =='.') continue;
								} catch(Exception e) {
										continue;
									}
								//add all wall that has not been visited to frontier list
								frontier.add(new Point(op.r+x,op.c+y,op));
							}
						}
					}
				}
			}catch(Exception e){
				continue;
			}
		}
			
		//mark end node
		if(frontier.isEmpty()){
			maz[last.r][last.c] ='E';
		}
		
		//print the maze
		for(int i = 0;i<r;i++){
			for(int j=0;j<c;j++){
				System.out.print(maz[i][j]);
			}
			System.out.println();
		}
	}
	
	static class Point{
		Integer r;
		Integer c;
		Point parent;
		public Point(int x, int y, Point p){
			r =x;
			c=y;
			parent=p;
		}
		
		public Point opposite(){
			if(this.r.compareTo(parent.r)!=0){
				return new Point(this.r + this.r.compareTo(parent.r), this.c,this);
			}
			if(this.r.compareTo(parent.c)!=0){
				return new Point(this.r,this.c + this.c.compareTo(parent.c),this);
			}
			return null;
		}
		
	}
}
				
			
						
			
			
			
			
