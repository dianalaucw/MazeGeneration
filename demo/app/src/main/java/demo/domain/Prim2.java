package demo.domain;

/**
* implmenting Prim's algorithm (second appraoch)
*/

import java.util.*;

public class Prim2 {
    static class Cell{
		
        //location of the cell inside maze
        Integer x,y;

        //initial parent node is null
		Cell parent = null;
        
        //indication of wether a wall exists
        boolean N,E,S,W,visited;

		public Cell(int x, int y, Point p){
            N = true;
            E=true;
            S = true;
            W = true;
            visited = false;
			this.x = x;
			this.y = y;
			this.parent=p;
		}

        //get x index
        public int getX() {
            return x;
        }

        //get y index
        public int getY() {
            return y;
        }

        //setters for the wall booleans
        public void setNorth(boolean b) {
            N = b;
        }

        public void setEast(boolean b) {
            E = b;
        }
        
        public void setSouth(boolean b) {
            S = b;
        }

        public void setWest(boolean b) {
            W = b;
        }

        //getters for the wall booleans
        public boolean getNorth() {
            return N;
        }
        
        public boolean getEast() {
            return E;
        }

        public boolean getSouth() {
            return S;
        }

        public boolean getWest() {
            return W;
        }

        //getter for visited
        public boolean getVisited() {
            return visited;
        }

        //setter for visited
        public void setVisited(boolean b) {
            visited = b;
        }

        //if x and y index are the same
        public boolean equals(Cell cell) {
            return (this.x == cell.getX() && this.y == cell.getY());
        }

        //if parent cell is the opposite cell
		public Cell opposite(){
			if(this.x.compareTo(parent.x)!=0){
				return new Cell(this.x + this.x.compareTo(parent.x), this.y,this);
			}
			if(this.x.compareTo(parent.y)!=0){
				return new Cell(this.x,this.y + this.y.compareTo(parent.y),this);
			}
			return null;
		}
		
	}

    static class Maze{
        private int length;
        private int width;
        private Cell[][] maz;
        private Cell start;

        private Map<Cell, List<Integer>> wallMap = new HashMap<>();
        private List<Cell> frontierList = new ArrayList<>();

        public Maze(int length, int width){
            this.length = length;
            this.width = width;
            maz = new Cell[width][length];
            for(int i =0;i<width;i++){
                for(int j=0;j<length;j++){
                    maz[i][j] = new Cell(i, j);
                }
            }
        }
    }

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
	
	
}