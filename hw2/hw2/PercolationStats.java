package hw2;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private double[] fractions;
    private int numTrials;

    // Perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N < 0 || T < 0){
            throw new IllegalArgumentException();
        }
        numTrials = T;
        fractions = new double[T];
        for (int i = 0; i < T; i++){
            int numOpened = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()){
                int r = StdRandom.uniform(N);
                int c = StdRandom.uniform(N);
                p.open(r,c);
            }
            fractions[i] = p.numberOfOpenSites()/(N*N);
        }

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence level
    public double confidenceLow(){
        double mu = mean();
        double sigma = stddev();
        return mu - (1.96 * sigma)/Math.sqrt(numTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        double mu = mean();
        double sigma = stddev();
        return mu + (1.96 * sigma)/Math.sqrt(numTrials);
    }
}
