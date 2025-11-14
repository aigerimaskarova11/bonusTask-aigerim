import java.util.Arrays;

public class SuffixArray {

    public static Integer[] buildSuffixArray(String s) {
        int n = s.length();
        // I create an array sa to store suffix starting positions
        Integer[] sa = new Integer[n];
        // I create a rank array to remember the rank of each suffix
        int[] rank = new int[n];
        // Temporary array to store new ranks after sorting
        int[] tmp = new int[n];

        // I initialize sa with positions 0..n-1 and rank with character codes
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = s.charAt(i);
        }

        // I use the doubling method: I compare prefixes of length k = 1,2,4,...
        for (int k = 1; k < n; k <<= 1) {
            final int K = k;

            // I sort suffixes by first rank and rank at distance k
            Arrays.sort(sa, (a, b) -> {
                if (rank[a] != rank[b]) return Integer.compare(rank[a], rank[b]);
                int ra = (a + K < n) ? rank[a + K] : -1;
                int rb = (b + K < n) ? rank[b + K] : -1;
                return Integer.compare(ra, rb);
            });

            // I compute new ranks for the sorted suffixes
            tmp[sa[0]] = 0; // first suffix gets rank 0
            for (int i = 1; i < n; i++) {
                int prev = sa[i - 1];
                int cur = sa[i];

                boolean diff = false; // I check if current suffix is different
                if (rank[prev] != rank[cur]) diff = true;

                int prevNext = prev + K < n ? rank[prev + K] : -1;
                int curNext = cur + K < n ? rank[cur + K] : -1;
                if (prevNext != curNext) diff = true;

                // I update the rank: if different, increase rank by 1
                tmp[cur] = tmp[prev] + (diff ? 1 : 0);
            }

            // I copy the new ranks back to rank array
            System.arraycopy(tmp, 0, rank, 0, n);

            // If all ranks are distinct, I can stop early
            if (rank[sa[n - 1]] == n - 1) break;
        }

        // I return the suffix array
        return sa;
    }

    public static int[] buildLCP(String s, Integer[] sa) {
        int n = s.length();
        int[] rank = new int[n]; // I remember the position of each suffix in sa

        // I fill rank array so rank[i] = position of suffix i in sa
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n]; // array to store LCPs
        int h = 0;              // current LCP length

        // I compute LCP for each suffix
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            if (r > 0) { // first suffix has no previous, so skip
                int j = sa[r - 1]; // previous suffix in sorted order
                // I count characters that match between i-th and previous suffix
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) {
                    h++;
                }
                lcp[r] = h;  // I save the LCP length
                if (h > 0) h--; // I decrease h for next suffix
            } else {
                lcp[0] = 0; // first suffix has LCP 0
            }
        }

        // I return the LCP array
        return lcp;
    }

    public static void printSAandLCP(String s, Integer[] sa, int[] lcp) {
        // print the suffix array and LCP nicely
        System.out.println("String: \"" + s + "\"");
        System.out.println("Index | SA  | LCP | Suffix");
        System.out.println("--------------------------------------");

        for (int i = 0; i < sa.length; i++) {
            // I print index, suffix start, LCP, and the suffix itself
            System.out.printf("%5d | %3d | %3d | %s%n", i, sa[i], lcp[i], s.substring(sa[i]));
        }
        System.out.println();
    }
}
