package percolation;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * Percolation Stats
 * @author Andrew Lewin
 * @author Mark Richardson
 *
 */
public class PercolationStats {

	private int numOfExperiments;
	private double[] openSitesRatio;

	/**
	 * Perform T independent experiments on an N‐by‐N grid
	 * @param N size of grid
	 * @param T independent experiments
	 */
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Constructor values can't be zero or less.");
		}
		
		numOfExperiments = T;
		openSitesRatio = new double[T];
		
		// Goes through T experiments and records the results
		for (int i = 0; i < numOfExperiments; i++) {
			Percolation newExperiment = new Percolation(N);
			int openSites = 0;
			
			// Single experiment
			while (newExperiment.percolates() == false) {
				int rand1 = StdRandom.uniform(N);
				int rand2 = StdRandom.uniform(N);
				if (newExperiment.isOpen(rand1, rand2) == false) {
					newExperiment.open(rand1, rand2);
					openSites += 1;
				}
			}
			
			// Record results of single experiment here before loop starts over
			openSitesRatio[i] = ((double) openSites) / (N * N);
		}
	}
	
	/**
	 * sample mean of percolation threshold
	 * @return mean
	 */
	public double mean() {
		return  StdStats.mean(openSitesRatio);
	}
	
	/**
	 * sample standard deviation of percolation 
	 * @return Standard deviation
	 */
	public double stddev() {
		return StdStats.stddev(openSitesRatio);
	}
	
	/**
	 * low  endpoint of 95% confidence interval
	 * @return low  endpoint of 95% confidence interval
	 */
	public double confidenceLow() {
		return (mean() - ((1.96 * stddev()) / Math.sqrt(numOfExperiments)));
	}
	
	/**
	 * high endpoint of 95% confidence interval
	 * @return high endpoint of 95% confidence interval
	 */
	public double confidenceHigh() {
		return (mean() + ((1.96 * stddev()) / Math.sqrt(numOfExperiments)));
	}
	
	public static void main(String [] args) {
		PercolationStats percStats = new PercolationStats(200,1000);
		StdOut.println("The Mean is:               " + percStats.mean());
		StdOut.println("The Standard Deviation is: " + percStats.stddev());
		StdOut.println("The Confidence Low is:     " + percStats.confidenceLow());
		StdOut.println("The Confidence Hight is:   " + percStats.confidenceHigh());
	}
	
}