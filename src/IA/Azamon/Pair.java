package IA.Azamon;

public class Pair implements Comparable<Pair> {
    private int x;
    private int y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Pair(){}
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public int compareTo(Pair o) {
        return(o.y-y); 
    }
    
    public String toString() {
        return "("+x+","+y+")";

    }

}
