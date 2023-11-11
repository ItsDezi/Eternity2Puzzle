import java.util.*;
public class Piece {

	private int side1;//on top
	private int side2;//right side
	private int side3;//bottom
	private int side4;//left side
	private boolean edge;//determines if piece is an edge piece
	private boolean corner;//determines if piece is a corner piece
	private int ID;
	private boolean used;//dictates whether piece is already used in this permutation
	//private boolean tried;
	
	//private boolean[][] tried = new boolean[10][10];//tests if a piece was tried in a certain position
	public Piece (int s1,  int s2, int s3, int s4, int id)
	{
		this.side1 = s1;
		this.side2 = s2;
		this.side3 = s3;
		this.side4 = s4;
		this.ID = id;
		if (s1 == 0 || s2 == 0 || s3 == 0 || s4 == 0)//tests for edge
		{
			this.edge = true;
			if((s1 == 0 && s2 ==0) || (s1 == 0 && s4 ==0) || (s2 == 0 && s3 == 0) || (s3 == 0 && s4 ==0))//tests for corner
			{
				this.corner = true;
			}
			else
			{
				this.corner = false;
			}
		}
		else
		{
			this.edge = false;
		}
		//tried = false;
	}
	
	public void rotate()//will always rotate right
	{
		int temp1 = this.side1;
		this.side1 = this.side4;
		this.side4 = this.side3;
		this.side3 = this.side2;
		this.side2 = temp1;
	}
	public void setID(int xx)
	{
		this.ID = xx;
	}
	public int getID()
	{
		return this.ID;
	}
	
	public Formatter temp_print()
	{
		Formatter fmt = new Formatter();
		//String o = this.side1 + " " + this.side2 +" " + this.side3 +" " + this.side4;
		if(this == null)
		{
			fmt.format("null");
			return fmt;
		}
		else
		{
		fmt.format("|%1s %1s %1s %1s|", this.side1,this.side2,this.side3,this.side4);
		return fmt;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void setedge(boolean xx)
	{
		this.edge = xx;
	}
	public boolean getedge()
	{
		return this.edge;
	}
	public void setused(boolean xx)
	{
		this.used = xx;
	}
	public boolean getused()
	{
		return this.used;
	}
	public int getside1()
	{
		return this.side1;
	}
	public int getside2()
	{
		return this.side2;
	}
	public int getside3()
	{
		return this.side3;
	}
	public int getside4()
	{
		return this.side4;
	}
	public boolean getcorner()
	{
		return this.corner;
	}
	/*public boolean gettried(int x, int y)
	{
		return this.tried[x][y];
	}
	public void settried(boolean xx, int x, int y)
	{
		this.tried[x][y] = xx;
	}*/
}
