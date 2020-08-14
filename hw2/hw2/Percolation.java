package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int size;
	private int list_size;
	private int open_size = 0;
	private WeightedQuickUnionUF uf;
	private boolean[][] open_list;


	// create N-by-N, with all sites initially blocked
	public Percolation (int N){
		size = N;
		list_size = N * N;
		open_list = new boolean[N][N];
		uf = new WeightedQuickUnionUF(N * N + 2); // 2 additional nodes: top and bottom
	}

	// open the site (row, col) if it is not open already
	public void open(int row, int col){
		int index = xyTo1D(row,col);
		if (!isOpen(row, col)){
			open_list[row][col] = true;
			open_size += 1;

			if (row == 0){
				uf.union(index, list_size);
			}

			if (row > 0 && open_list[row - 1][col]){
				uf.union(index, xyTo1D(row - 1, col));
			}

			if (row < size - 1 && open_list[row + 1][col]){
				uf.union(index, xyTo1D(row + 1, col));
			}

			if (col > 0  && open_list[row][col - 1]){
				uf.union(index, xyTo1D(row, col - 1));
			}

			if (col < size - 1  && open_list[row][col + 1]){
				uf.union(index, xyTo1D(row, col + 1));
			}

			if (row == size - 1 && !percolates()){
				uf.union(index, list_size + 1);
			}

		}
		
		
	}

	// is the site(row, col) open?
	public boolean isOpen(int row, int col){
		return open_list[row][col];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col){
		int index = xyTo1D(row,col);
		return uf.connected(index, list_size);
	}

	// number of open sites
	public int numberOfOpenSites(){
		return open_size;
	}

	// does the system percolate?
	public boolean percolates(){
		return uf.connected(list_size, list_size + 1);
	}

	// 1. helper method: open(3,4), open(2,4), union(14,19)
	public int xyTo1D(int row, int col){
		return size * row + col;
	}

	// use for unit testing (not required, but keep this here for the autograder)
	public static void main(String[] args){

	}



}
