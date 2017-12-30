/*
@Author:Shiqi Jia
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] results;
    private final double CONFIDENCE_95;
    private final double mean;
    private final double std;


    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validate(n);
        validate(trials);
        results = new double[trials];
        CONFIDENCE_95 = 1.96;

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
        //better than calculate later.
        mean = StdStats.mean(results);
        std = StdStats.stddev(results);
    }

    private void validate(int p) {

        if (p <= 0) {
            throw new IllegalArgumentException("index " + p + " is not less than 1");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return std;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - CONFIDENCE_95 * std / Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + CONFIDENCE_95 * std / Math.sqrt(results.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, t);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println("lo" + test.confidenceLo());
        System.out.println("Hi" + test.confidenceHi());
    }
}
