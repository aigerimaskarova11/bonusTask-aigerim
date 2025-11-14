public class Main {

    public static void main(String[] args) {
        // I am testing my suffix array and LCP algorithm on three strings
        runExample("aigerim");        // short string
        runExample("lolololololo");   // repeated pattern
        runExample("jeonjungkook");   // longer name
    }

    private static void runExample(String s) {
        // I build the suffix array for my string
        Integer[] sa = SuffixArray.buildSuffixArray(s);

        // I build the LCP array to know common prefixes between sorted suffixes
        int[] lcp = SuffixArray.buildLCP(s, sa);

        // print the suffix array and LCP to see how it looks
        SuffixArray.printSAandLCP(s, sa, lcp);

        // I find the longest repeated substring using the LCP array
        int max = 0;  // max length I have found so far
        int pos = -1; // starting index of that substring
        for (int i = 1; i < lcp.length; i++) {
            if (lcp[i] > max) {
                max = lcp[i];    // I update max
                pos = sa[i];     // I remember where the substring starts
            }
        }

        // the result
        if (max > 0) {
            System.out.println("Longest repeated substring: \"" +
                    s.substring(pos, pos + max) + "\" with length " + max);
        } else {
            // If there was no repeated substring
            System.out.println("No repeated substring found.");
        }

        System.out.println("==================================================\n");
    }
}
