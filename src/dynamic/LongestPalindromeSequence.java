package dynamic;

public class LongestPalindromeSequence {

    public int findLPSLength(String st) {
        return findLPSLengthRecursive(st, 0, st.length() - 1);
    }

    private int findLPSLengthRecursive(String st, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return 0;
        if (startIndex == endIndex)
            return 1;
        if (st.charAt(startIndex) == st.charAt(endIndex))
            return 2 + findLPSLengthRecursive(st, startIndex + 1, endIndex - 1);
        int c1 = findLPSLengthRecursive(st, startIndex + 1, endIndex);
        int c2 = findLPSLengthRecursive(st, startIndex, endIndex - 1);
        return Math.max(c1, c2);
    }

    public int findLPSLengthTopDownDp(String st) {
        Integer[][] dp = new Integer[st.length()][st.length()];
        return findLPSLengthRecursiveTopDownDp(dp, st, 0, st.length() - 1);
    }

    private int findLPSLengthRecursiveTopDownDp(Integer[][] dp, String st, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return 0;
        if (startIndex == endIndex)
            return 1;
        if (dp[startIndex][endIndex] == null) {
            if (st.charAt(startIndex) == st.charAt(endIndex)) {
                dp[startIndex][endIndex] = 2 + findLPSLengthRecursiveTopDownDp(dp, st, startIndex + 1, endIndex - 1);
            } else {
                int c1 = findLPSLengthRecursiveTopDownDp(dp, st, startIndex + 1, endIndex);
                int c2 = findLPSLengthRecursiveTopDownDp(dp, st, startIndex, endIndex - 1);
                dp[startIndex][endIndex] = Math.max(c1, c2);
            }
        }
        return dp[startIndex][endIndex];
    }

    public static void main(String[] args) {
        LongestPalindromeSequence lps = new LongestPalindromeSequence();
        System.out.println(lps.findLPSLength("abdbca"));
        System.out.println(lps.findLPSLength("cddpd"));
        System.out.println(lps.findLPSLength("pqr"));

        System.out.println(lps.findLPSLengthTopDownDp("abdbca"));
        System.out.println(lps.findLPSLengthTopDownDp("cddpd"));
        System.out.println(lps.findLPSLengthTopDownDp("pqr"));
    }

}
