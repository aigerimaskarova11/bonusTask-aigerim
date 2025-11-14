# **Suffix Array with LCP using Kasai’s Algorithm**
## **1. Introduction**

In this project, I implemented a **Suffix Array** for a string along with the **LCP (Longest Common Prefix) array** using **Kasai’s algorithm**.
The purpose was to efficiently find repeated substrings and understand how suffix arrays work with different strings.

I chose three example strings to test my implementation:

1. `"aigerim"` — a short string
2. `"lolololololo"` — a string with repeated patterns
3. `"jeonjungkook"` — a longer string with no obvious repeated substrings

## **2. Implementation**

I created two Java classes:

1. **`SuffixArray.java`** — contains the main logic:

   * I first build the suffix array using the **doubling method**, which sorts suffixes based on increasing prefix lengths.
   * Then, I compute the **LCP array** using Kasai’s algorithm. This allows me to find the length of the longest common prefix between consecutive suffixes efficiently.
   * Finally, I print the suffix array, the LCP array, and the suffixes for visual verification.

2. **`Main.java`** — for testing:

   * I call my methods on the three example strings.
   * I search for the **longest repeated substring** using the LCP array.
   * I print the results in a clear table format.

**Example of how I find the longest repeated substring:**
I loop through the LCP array, store the maximum value, and then extract the substring from the original string.

## **3. Testing Results**

I ran my program on the three custom strings. The results were as follows:

1. `"aigerim"`

   * Suffixes and LCP printed correctly.
   * **No repeated substring found**, as expected.

2. `"lolololololo"`

   * The suffix array and LCP array show repeated patterns clearly.
   * **Longest repeated substring:** `"lololololo"` with length 10.

3. `"jeonjungkook"`

   * The suffix array and LCP array printed correctly.
   * **Longest repeated substring:** `"ook"` with length 3.

From these tests, I verified that the algorithm works for strings of different lengths and repeated patterns.

## **4. Complexity Analysis**

**Suffix Array (Doubling Method):**

* Time: O(n (log n)²) — because I sort the suffixes log n times and each sort takes O(n log n).
* Space: O(n) — arrays for suffix positions, ranks, and temporary ranks.

**LCP Array (Kasai’s Algorithm):**

* Time: O(n) — computes LCPs in linear time by using previous results.
* Space: O(n) — array to store LCP and rank positions.

**Overall:**

* Time: O(n (log n)²)
* Space: O(n)

The algorithm handles short, medium, and long strings efficiently. Kasai’s algorithm allows fast LCP computation without extra sorting.

## **5. Conclusion**

I implemented a **suffix array with LCP** from scratch and tested it on three strings.

* I successfully computed the suffix array and LCP for all examples.
* I could find the longest repeated substrings.
* The implementation is efficient for medium-length strings.
This project helped me understand how suffix arrays and LCP work together to solve string problems. The code is modular, with `SuffixArray.java` handling the algorithm and `Main.java` handling tests.

