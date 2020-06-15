public class Triangle{
	public static void drawTriangle(int n){
		int col = 0;
		int row = 0;
		int SIZE = n;
		while (row < SIZE){
			while (col <= row){
				System.out.print('*');
				col += 1;
			}
			System.out.println();
			row += 1;
			col = 0;
		}	
	}


	public static void main(String[] args){
		drawTriangle(Integer.parseInt(args[0]));
	}
}
