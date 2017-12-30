/*
@author:Shiqi Jia

 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
    private final int side;
    //This array is used to mark open/close state
    private byte[][] open;
    private int count;

    /**
     * constructor.
     *
     * @param n the side length
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is not >0");
        }
        //1.solve index transfer[0,n-1],[1,n];
        // 2.leave space for virtual top and virtual bottom
        int m = n + 1;
        this.uf = new WeightedQuickUnionUF(m * m);
        this.uf2 = new WeightedQuickUnionUF(m * m);
        this.side = n;
        open = new byte[m][m];
        count = 0;
    }

    //validate column/row input,[1,n]
    private void validate(int p) {
        int n = side;
        if (p < 1 || p > n) {
            throw new IllegalArgumentException("index " + p + " is not between 1 and " + n);
        }
    }


    /**
     * open a grid given row and col.
     *
     * @param row the index of row
     * @param col the index of column
     */
    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (open[row][col] == 1) return;
        if (open[row][col] == 0) {
            open[row][col] = 1;
            count++;
        }
        //0 is the virtual top.
        //2 is another top to avoid backwash
        if (row == 1) {
            uf.union(row * side + col, 0);
            uf2.union(row * side + col, 0);

//            uf.union(row * side + col,top );
            if (row + 1 <= side && isOpen(row + 1, col)) {
                uf.union(row * side + col, (row + 1) * side + col);
                uf2.union(row * side + col, (row + 1) * side + col);
            }
            if (col - 1 >= 1 && isOpen(row, col - 1)) {
                uf.union(row * side + col, row * side + col - 1);
                uf2.union(row * side + col, row * side + col - 1);
            }
            if (col + 1 <= side && isOpen(row, col + 1)) {
                uf.union(row * side + col, row * side + col + 1);
                uf2.union(row * side + col, row * side + col + 1);

            }

        }
        //1 is the virtual bottom
        if (row == side) {
            uf.union(row * side + col, 1);
            if (row - 1 >= 1 && isOpen(row - 1, col)) uf.union(row * side + col, (row - 1) * (side) + col);
            if (col - 1 >= 1 && isOpen(row, col - 1)) uf.union(row * side + col, row * side + col - 1);
            if (col + 1 <= side && isOpen(row, col + 1)) uf.union(row * side + col, row * side + col + 1);


            if (row - 1 >= 1 && isOpen(row - 1, col)) uf2.union(row * side + col, (row - 1) * (side) + col);
            if (col - 1 >= 1 && isOpen(row, col - 1)) uf2.union(row * side + col, row * side + col - 1);
            if (col + 1 <= side && isOpen(row, col + 1)) uf2.union(row * side + col, row * side + col + 1);

        }
        //connect nearby open grids
        if (row > 1 && row < side) {
            if (isOpen(row - 1, col)) uf.union(row * side + col, (row - 1) * side + col);
            if (isOpen(row + 1, col)) uf.union(row * side + col, (row + 1) * side + col);
            if (col > 1 && isOpen(row, col - 1)) uf.union(row * side + col, row * side + col - 1);
            if (col < side && isOpen(row, col + 1)) uf.union(row * side + col, row * side + col + 1);

            if (isOpen(row - 1, col)) uf2.union(row * side + col, (row - 1) * side + col);
            if (isOpen(row + 1, col)) uf2.union(row * side + col, (row + 1) * side + col);
            if (col > 1 && isOpen(row, col - 1)) uf2.union(row * side + col, row * side + col - 1);
            if (col < side && isOpen(row, col + 1)) uf2.union(row * side + col, row * side + col + 1);
        }

    }

    /**
     * if a given grid is open
     *
     * @param row given row
     * @param col given column
     * @return true if the given grid is open
     */
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        if (open[row][col] == 1) return true;
        return false;
    }

    /**
     * if the input grid could connect top grid
     *
     * @param row x-coordinate
     * @param col y-coordinate
     * @return true if the grid is connected to top
     */
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        return (uf2.connected(0, (row * side + col)));
    }

    /**
     * the number of open sites
     *
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return count;
    }

    /**
     * test if the matrix is percolated
     *
     * @return true if the matrix is percolated
     */
    public boolean percolates() {
        if (uf.connected(0, 1)) return true;
        return false;
    }

    public static void main(String[] args) {
//        Percolation test = new Percolation(3);
//        test.open(3, 2);
//        test.open(1, 3);
//        test.open(2, 3);
//        test.open(3, 3);
////        test.open(4, 4);
//        System.out.println(test.isFull(3,1));
    }  // test client (optional)
}