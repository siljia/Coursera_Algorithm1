/*
@author:Shiqi Jia

 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    final private WeightedQuickUnionUF uf;
    final private int side;
    //This array is used to mark open/close state
    private byte[][] open;
    private int count;
    //validate column/row input,[1,n]
    private void validate(int p) {
        int n = side;
        if (p < 1 || p > n) {
            throw new IllegalArgumentException("index " + p + " is not between 1 and " + n);
        }
    }

    //constructer
    public Percolation(int n) {
        //1.solve index transfer[0,n-1],[1,n];
        // 2.leave space for virtual top and virtual bottom
        int m = n + 1;
        this.uf = new WeightedQuickUnionUF(m * m);
        this.side = n;
        open = new byte[m][m];
        count=0;
    }

    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (open[row][col] == 1) return;
        if (open[row][col] == 0) {
            open[row][col] = 1;
            count++;
        }
//0 is the virtual top
        if (row == 1) {
            uf.union(row * side + col, 0);
            if (row + 1 <= side && isOpen(row + 1, col)) uf.union(row * side + col, (row + 1) * side + col);
            if (col - 1 >= 1 && isOpen(row, col - 1)) uf.union(row * side + col, row * side + col - 1);
            if (col + 1 <= side && isOpen(row, col + 1)) uf.union(row * side + col, row * side + col + 1);

        }
        //1 is the virtual bottom
        if (row == side) {
            uf.union(row * side + col, 1);
            if (row - 1 >= 1 && isOpen(row - 1, col)) uf.union(row * side + col, (row - 1) * (side) + col);
            if (col - 1 >= 1 && isOpen(row, col - 1)) uf.union(row * side + col, row * side + col - 1);
            if (col + 1 <= side && isOpen(row, col + 1)) uf.union(row * side + col, row * side + col + 1);

        }


//connect nearby open grids
        if (row > 1 && row < side) {
            if (isOpen(row - 1, col)) uf.union(row * side + col, (row - 1) * side + col);
            if (isOpen(row + 1, col)) uf.union(row * side + col, (row + 1) * side + col);
            if (col > 1 && isOpen(row, col - 1)) uf.union(row * side + col, row * side + col - 1);
            if (col < side && isOpen(row, col + 1)) uf.union(row * side + col, row * side + col + 1);
        }

    }

    public boolean isOpen(int row, int col) {
        if (open[row][col] == 1) return true;
        return false;
    }

    public boolean isFull(int row, int col) {
        if (uf.connected(0, (row * side + col))) return true;
        return false;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        if (uf.connected(0, 1)) return true;
        return false;
    }

    public static void main(String[] args) {
//        Percolation test = new Percolation(2);
//        test.open(2, 2);
//        test.open(1, 1);
//        test.open(1, 2);
//        test.open(1, 2);
////        test.open(4, 4);
//        System.out.println(test.numberOfOpenSites());
    }  // test client (optional)
}