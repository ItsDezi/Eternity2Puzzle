import java.io.FileNotFoundException;
	import java.util.*;
	import java.io.File;
	
public class board_10x10 {

		private Piece[][] b = new Piece[12][12];//board will be surrounded by "artificial" edge pieces
		private Piece[] all_pieces = new Piece[100];
		int number_of_rows = 10;//number of real pieces(not artificial edge ones)
		int n = 10;//since its always an nXn grid, I just added this to simplify things
		int number_of_columns = 10;//number of real pieces(not artificial edge ones)
		int pieceID = 0;
		Stack<Piece> stack = new Stack<Piece>();//backbone of the backtracking
		int[][] stack_height = new int[10][10];
		//15 different pieces in the 10X10
		int row = 1;//W.R.T board b
		int col = 1;//W.R.T board b
		
		Piece[] arr0 = new Piece[64];//all pieces with a side of value 0
		Piece[] arr1 = new Piece[24];//all pieces with a side of value 1
		Piece[] arr2 = new Piece[24];//all pieces with a side of value 2
		Piece[] arr3 = new Piece[24];//all pieces with a side of value 3
		Piece[] arr4 = new Piece[48];//all pieces with a side of value 4
		Piece[] arr5 = new Piece[48];//all pieces with a side of value 5
		Piece[] arr6 = new Piece[48];//all pieces with a side of value 6
		Piece[] arr7 = new Piece[50];//all pieces with a side of value 7
		Piece[] arr8 = new Piece[50];//all pieces with a side of value 8
		Piece[] arr9 = new Piece[50];//all pieces with a side of value 9
		Piece[] arr10 = new Piece[50];//all pieces with a side of value 10
		Piece[] arr11 = new Piece[50];//all pieces with a side of value 11
		Piece[] arr12 = new Piece[50];//all pieces with a side of value 12
		Piece[] arr13 = new Piece[30];//all pieces with a side of value 13
		Piece[] arr14 = new Piece[30];//all pieces with a side of value 14
		/*Piece[] arr15 = new Piece[50];//all pieces with a side of value 15
		Piece[] arr16 = new Piece[48];//all pieces with a side of value 16
		Piece[] arr17 = new Piece[48];//all pieces with a side of value 17
		Piece[] arr18 = new Piece[50];//all pieces with a side of value 18
		Piece[] arr19 = new Piece[50];//all pieces with a side of value 19
		Piece[] arr20 = new Piece[50];//all pieces with a side of value 20
		Piece[] arr21 = new Piece[50];//all pieces with a side of value 21
		Piece[] arr22 = new Piece[50];//all pieces with a side of value 22*/

		Piece [][] arr_of_arrs = new Piece[][] {arr0, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14};//, arr15, arr16, arr17, arr18, arr19, arr20, arr21, arr22};
		public board_10x10(String fileName)
		{
			int count = 0;
			Scanner ss;
			File file = new File(fileName);
			try {
				ss = new Scanner(file);
				ss.nextLine();
	        while(ss.hasNextInt())
	        {
	    		int s1 = ss.nextInt();
	    		int s2 = ss.nextInt();
	    		int s3 = ss.nextInt();
	    		int s4 = ss.nextInt();
	    		all_pieces[count] = new Piece(s1,s2,s3,s4, pieceID++);
	    		count++;

	        }
	        ss.close();
			}
			 catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Piece artificial_edge = new Piece(0,0,0,0, 420);
	        for (int i = 0; i < b[0].length ;i++)//makes artificial edges all around
	        {
	        	b[i][0] = artificial_edge;
	        	b[0][i] = artificial_edge;
	        	b[i][n+1] = artificial_edge;
	        	b[n+1][i] = artificial_edge;
	        }
	        

	        for (int i = 0; i< arr_of_arrs.length; i++)//23 is the number of different sides that can appear
	        {
	        	int k = 0;
	        	for (int j = 0; j < all_pieces.length;j++)
	        	{
	        		//for(int k = 0; k < arr_of_arrs[i][j].length;k++)
	        		//{
	        		if(all_pieces[j].getside1() == i)
	        		{
	        			arr_of_arrs[i][k] = all_pieces[j];
	        			k++;
	        		}
	        		else if(all_pieces[j].getside2() == i)
	        		{
	        			arr_of_arrs[i][k] = all_pieces[j];
	        			k++;
	        		}
	        		else if(all_pieces[j].getside3() == i)
	        		{
	        			arr_of_arrs[i][k] = all_pieces[j];
	        			k++;
	        		}
	        		else if(all_pieces[j].getside4() == i)
	        		{
	        			arr_of_arrs[i][k] = all_pieces[j];
	        			k++;
	        		}
	        	//}
	        	}
	        }
	        }
		

	        public void compare_subarrays(Piece lefty, Piece toppy, Piece righty, Piece bottom)
	        {
	        	int count = 0;
	        	int r = row-1;//WRT stack_height
	        	int c = col-1;//WRT stack_height
	        	Piece[] a = new Piece[48];
	        	if(toppy == null || lefty == null)
	        	{
	        		//idk what to do...
	        	}
	        	int left = lefty.getside2();
	        	int top = toppy.getside3();
	        	if (righty == null && bottom == null)
	        	{
	        		for(Piece i:arr_of_arrs[left])
	        		{
	        			for(Piece j:arr_of_arrs[top])
	        			{
	        				if(j!=null && i!=null && j.getID() == i.getID())
	        				{
	        					if(i.getside1() == top && i.getside4()== left&& i.getside2() !=0 && i.getside3() != 0)
	        					{
	        						a[count] = i;
	        						count++;
	        					}
	        					else if(i.getside1() == left && i.getside2() == top && i.getside3() !=0 && i.getside4() != 0)
	        					{
	        						a[count] = i;
	        						count++;
	        					}
	        					else if(i.getside2() == left && i.getside3() == top && i.getside1() !=0 && i.getside4() != 0)
	        					{
	        						a[count] = i;
	        						count++;
	        					}
	        					else if(i.getside3() == left && i.getside4() == top && i.getside2() !=0 && i.getside1() != 0)
	        					{
	        						a[count] = i;
	        						count++;
	        					}
	        				}
	        			}
	        		}
	        	}
	        	else if(righty != null && bottom == null)
	        	{
	        		int right = righty.getside4();
	        		for(Piece k:arr_of_arrs[right])
	        		{
		        		for(Piece i:arr_of_arrs[left])
		        		{
		        			for(Piece j:arr_of_arrs[top])
		        			{
		        				if(i !=null && j!=null && k!=null && k.getID() == j.getID() && k.getID() == i.getID())
		        				{
		        					if(i.getside1() ==top  && i.getside2() == right && i.getside4() == left && i.getside3() != 0)
		        					{
		        						a[count] = i;
		        						count++;
		        					}
		        					else if(i.getside1() == left && i.getside2() == top && i.getside3() == right && i.getside4() != 0)
		        					{
		        						a[count] = i;
		        						count++;
		        					}
		        					else if(i.getside2() == left && i.getside3() == top && i.getside4() == right && i.getside1() != 0)
		        					{
		        						a[count] = i;
		        						count++;
		        					}
		        					else if(i.getside3() == left && i.getside4() == top && i.getside1() == right && i.getside2() != 0)
		        					{
		        						a[count] = i;
		        						count++;
		        					}
		        				}
		        			}
		        		}
	        		}
	        	}
	        	else if(righty == null && bottom != null)
	        	{
	        		int botty = bottom.getside1();
	        		for(Piece k:arr_of_arrs[botty])
	        		{
		        		for(Piece i:arr_of_arrs[left])
		        		{
		        			for(Piece j:arr_of_arrs[top])
		        			{
		        				if(i !=null && j!=null && k!=null && k.getID() == j.getID() && k.getID() == i.getID())
		        				{
		        				if(i.getside1() == top && i.getside3() == botty && i.getside4() == left && i.getside2() != 0)
		        				{
		        					a[count] = i;
	        						count++;
		        				}
		        				else if(i.getside1() == left && i.getside4() == botty && i.getside2() == top && i.getside3() != 0)
		        				{
		        					a[count] = i;
	        						count++;
		        				}
		        				else if(i.getside2() == left && i.getside1() == botty && i.getside3() == top && i.getside4() != 0)
		        				{
		        					a[count] = i;
	        						count++;
		        				}
		        				else if(i.getside3() == left && i.getside2() == botty && i.getside4() == top&& i.getside1() != 0)
		        				{
		        					a[count] = i;
	        						count++;
		        				}
		        				}
		        			}
		        		}
	        		}
	        	}
	        	else if(righty != null && bottom != null)
	        	{
	        		int botty = bottom.getside1();
	        		int right = righty.getside4();
	        		for(Piece k:arr_of_arrs[botty])
	        		{
		        		for(Piece i:arr_of_arrs[left])
		        		{
		        			for(Piece j:arr_of_arrs[top])
		        			{
		        				for(Piece q: arr_of_arrs[right])
		        				{
		        					if(i != null && j!= null && k!=null && q != null &&k.getID() == j.getID() && k.getID() == i.getID() && k.getID() == q.getID() && i.getcorner() ==true)
		        					{
		        						if(i.getside1() == top && i.getside2() == right && i.getside3() == botty & i.getside4() == left)
		        						{
		        							a[count] = i;
		        							count++;
		        						}
		        						else if(i.getside2() == top && i.getside3() == right && i.getside4() == botty & i.getside1() == left)
		        						{
		        							a[count] = i;
		        							count++;
		        						}
		        						else if(i.getside3() == top && i.getside4() == right && i.getside1() == botty & i.getside2() == left)
		        						{
		        							a[count] = i;
		        							count++;
		        						}
		        						else if(i.getside4() == top && i.getside1() == right && i.getside2() == botty & i.getside3() == left)
		        						{
		        							a[count] = i;
		        							count++;
		        						}
		        					}
		        				}
		        			}
		        		}
	        		}
	        	}
		        		
	        
	        	for(Piece i:a)
	        	{
	        		if(i != null)
	        		{
	        			for(Piece y:all_pieces)
	        			{
	        				if(y != null && i.getID() == y.getID() && y.getused() == false)
	        				{
	        					stack.push(i);
	        					break;
	        				}
	        			}
	        		}
	        	}
	        	stack_height[r][c] = stack.size();

	        	//Backtrack();

	        }
	        public void Backtrack()
	        {
	        	int r = row-1;
	        	int c = col-1;
	        	Piece z;
	        	if(stack_height[r][c] == get_last_stack_height() + 1)
	        	{
	        		//what to do here?
	        		b[row][col] = null;
	        		z = stack.pop();
	        		for(Piece y:all_pieces)
	        		{
	        			if(z.getID() == y.getID()) 
	        			{
	        				y.setused(false);
	        				break;
	        			}
	        		}
	        		previous_piece();
	        		stack_height[r][c] = 0;

	        		Backtrack();
	        	}

	        	else if(stack_height[r][c] > get_last_stack_height() + 1)
	        	{
	        		z = stack.pop();
	        		--stack_height[r][c];
	        		for(Piece y:all_pieces)
	        		{
	        			if(z.getID() == y.getID()) 
	        			{
	        				y.setused(false);
	        			}
	        			if(y.getID() == stack.peek().getID() && y.getused() == false)
	        			{
	        				b[row][col] = stack.peek();
	        				rotate_until_fits();
	        				y.setused(true);
	        			}
	        		}
	        	}
	        }
	        public void sort()
	        {
	        	int max_row = 1;
	        	int max_col = 1;
	        	while(row < b.length-1) {
	        		col=1;//resets global column pointer
	        		max_col = 1;
	        		while(col<b.length-1)
	        		{
	        			Piece toppy = b[row-1][col];
	        			Piece lefty = b[row][col-1];
	        			//System.out.print("\n\n" + printboard() + "\n\nstack_height = "+ stack_height[row-1][col-1] +  "row = " + row + "   col = " + col + "\n\nLefty = " + lefty.temp_print() + " Toppy = " + toppy.temp_print()); //+"Righty = "+righty.temp_print());

	        			Piece righty = b[row][col+1];
	        			Piece bottom = b[row+1][col];

        					compare_subarrays(lefty, toppy, righty, bottom);//maybe only do this if stack height changes
        					if(stack_height[row-1][col-1] == get_last_stack_height())
        					{
        						previous_piece();
        						Backtrack();
        					}

        					for(Piece i:all_pieces)
        					{
        						if (/*i.gettried(row-1, col-1) == false &&*/ i.getID() == stack.peek().getID() && i.getused() == false /*&& stack_height[row-1][col-1] - get_last_stack_height() >0*/)
        						{
        							i.setused(true);
                					b[row][col] = stack.peek();
                					//i.settried(true, row-1,col-1);
        							break;
        						}
        					
        					}

        					rotate_until_fits();
        					max_col = col;
        					if(row>max_row)
        					{
        						max_row = row;
        						if(col>max_col)
        						{
        							max_col = col;
        						}
        					}
        					System.out.print("\n\nMax row = " + max_row + "\nMax column = " + max_col);
	        			col++;
	        		}
		        	row++;

	        	}
	        	System.out.print("\n\n" + printboard());
	        }

	        public void rotate_until_fits()
	        {	   
	        Piece righty = b[row][col+1];
			Piece bottom = b[row+1][col];
			Piece toppy = b[row-1][col];
			Piece lefty = b[row][col-1];
			int top = toppy.getside3();
			int left = lefty.getside2();
				int count=0;
				while(count<4)
				{
					if(righty == null && bottom != null && b[row][col].getside1() == top && b[row][col].getside4() == left && b[row][col].getside3() == bottom.getside1())
					{
					break;
					}
					else if(righty != null && bottom == null && b[row][col].getside1() == top && b[row][col].getside4() == left && b[row][col].getside2() == righty.getside4())
					{
						break;
					}
					else if(righty != null && bottom != null && b[row][col].getside1() == top && b[row][col].getside4() == left && b[row][col].getside2() == righty.getside4() && b[row][col].getside3() == bottom.getside1())
					{
						break;
					}

					else if(righty ==null && bottom == null && b[row][col].getside1() == top && b[row][col].getside4() == left)
					{
						break;
					}
					else
					{	
					b[row][col].rotate();
					count++;
					}
				}
	        }

	       
	        public void previous_piece()
	        {
	        	if(col == 1 && row>1)
				{
	        		stack_height[row-1][col-1] = 0;
					col +=9;
					row--;
				}
				else
				{
					stack_height[row-1][col-1] = 0;
					col--;
				}
	        }
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			board_10x10 bb = new board_10x10("sides_10x10.txt");
	
			//System.out.print("\n\n\n\n" + bb.printboard());
			long start_time = System.currentTimeMillis();
			bb.sort();
			long end_time = System.currentTimeMillis();
			System.out.print("\n\nTotal runtime: " + (end_time - start_time) + " ms");
	        Runtime runtime = Runtime.getRuntime();

	        runtime.gc();//garbage collector

	        long memory = runtime.totalMemory() - runtime.freeMemory();
	        System.out.println("\n\nUsed memory in bytes: " + memory + "\n");
		}
	        
		public int get_last_stack_height()
		{
			int c= col-1;//wrt stack_height
			int r = row-1;//wrt to stack height
			int last;
			if(c == 0 && r != 0)
			{
				last = stack_height[r-1][c+9];
			}
			else if(c == 0 && r == 0)
			{
				last = 0;
			}
			else if(c > 0 && c%10 == 0)
				{
					last = stack_height[r-1][c+9];
				}
			else 
			{
				last = stack_height[r][c-1];
			}
			return last;
		}

		
        public Formatter printboard()
        {
        	//b[2][8] = new Piece(1,2,3,4,8);

        	Formatter fmt = new Formatter();
        	//String output = "";

        	for(int i = 0; i < number_of_columns + 2; i++)
        	{
        		for(int j = 0; j < number_of_rows + 2;j++)
        		{
        			if(b[i][j] == null)
        			{
        				fmt.format("%15s","NULL");
        				//break;
        			}
        			else {
        			fmt.format("%15s",b[i][j].temp_print());
        			}
        			//output += b[j][i].temp_print() + " | ";
        		}
        		fmt.format("\n\n");
        		//output +=  "\n\n";
        	}
        	return fmt;
        }

        
	}
