package searchingApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/*************************************************************************
 *  Compilation:  javac LookupCSV.java
 *  Execution:    java LookupCSV file.csv keyField valField
 *  Dependencies: ST.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/35applications/DJIA.csv
 *                http://algs4.cs.princeton.edu/35applications/UPC.csv
 *                http://algs4.cs.princeton.edu/35applications/amino.csv
 *                http://algs4.cs.princeton.edu/35applications/elements.csv
 *                http://algs4.cs.princeton.edu/35applications/ip.csv
 *                http://algs4.cs.princeton.edu/35applications/morse.csv
 *  
 *  Reads in a set of key-value pairs from a two-column CSV file
 *  specified on the command line; then, reads in keys from standard
 *  input and prints out corresponding values.
 * 
 *  % java LookupCSV amino.csv 0 3     % java LookupCSV ip.csv 0 1 
 *  TTA                                www.google.com 
 *  Leucine                            216.239.41.99 
 *  ABC                               
 *  Not found                          % java LookupCSV ip.csv 1 0 
 *  TCT                                216.239.41.99 
 *  Serine                             www.google.com 
 *                                 
 *  % java LookupCSV amino.csv 3 0     % java LookupCSV DJIA.csv 0 1 
 *  Glycine                            29-Oct-29 
 *  GGG                                252.38 
 *                                     20-Oct-87 
 *                                     1738.74
 *
 *
 *************************************************************************/

public class StocksApp {
	// date formatter
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void main(String[] args) throws ParseException {
    	int valField = 1;
    	String fileName = "src/searchingApp/Toyota.csv";
        RedBlackBST<Date, Double> st = readInDataFromCsv(valField, fileName);
        
        StdOut.println("Most recent date: " + formatter.format(st.max()));
        StdOut.println("Least recent date: " + formatter.format(st.min()));
        Date firstDateIn2005 = st.ceiling(formatter.parse("2005-01-01"));
        StdOut.println("Closing value of first available date in 2005: " + 
        		st.get(firstDateIn2005));
        
        StdOut.println("All opening values of december 2012:");
        Date december2012begin = st.ceiling(formatter.parse("2012-12-01"));
        Date december2012end = st.floor(formatter.parse("2012-12-31"));
        for (Date date : st.keys(december2012begin, december2012end)) {
        	StdOut.println(formatter.format(date) + ": $" + st.get(date));
        }
        
        StdOut.println("Opening value of last available date in 2009: " + st.get(st.floor(formatter.parse("2009-12-31"))));
        StdOut.println("Most recent opening value: " + st.get(st.floor(formatter.parse("2015-04-06"))));
        
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            Date s = formatter.parse(input);
            if (st.contains(s)) StdOut.println("Cosing price on " + formatter.format(s) + ": " + st.get(s));
            else                StdOut.println("Not found");
        }
    }
    
    private static RedBlackBST<Date, Double> readInDataFromCsv(int valField, String stockFile) throws ParseException {
    	final int KEY_FIELD = 0; // column 0

        // symbol table
        RedBlackBST<Date, Double> st = new RedBlackBST<>();
        
        // number read in
        int readCount = 0;
        
        // read in the data from csv file
        In in = new In(stockFile);
        in.readLine();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String date = tokens[KEY_FIELD];
            Date key = formatter.parse(date);
            Double val = Double.parseDouble(tokens[valField]);
            st.put(key, val);
            readCount++;
        }
        StdOut.println("Records read: " + readCount);
        return st;
    }
}