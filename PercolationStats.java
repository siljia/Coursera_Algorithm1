/*
@Author:Shiqi Jia
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.lang.Math;


public class PercolationStats {
    final private double[] results;
//    final private double size;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        results = new double[trials];
        double size = n * n;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            double count = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    count++;
                }
            }
            results[i] = count / (size * 1.0);

        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(results.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, t);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println(test.confidenceLo());
        System.out.println(test.confidenceHi());
    }
}
