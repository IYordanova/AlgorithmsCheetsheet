package dynamic;

public class LongestCommonString {

    public int findLCSLength(String s1, String s2) {
        return findLCSLengthRecursive(s1, s2, 0, 0, 0);
    }

    private int findLCSLengthRecursive(String s1, String s2, int i1, int i2, int count) {
        if (i1 == s1.length() || i2 == s2.length())
            return count;
        if (s1.charAt(i1) == s2.charAt(i2)) // current chars in both are same
            count = findLCSLengthRecursive(s1, s2, i1 + 1, i2 + 1, count + 1);
        int c1 = findLCSLengthRecursive(s1, s2, i1, i2 + 1, 0);  // starting count over, move the second
        int c2 = findLCSLengthRecursive(s1, s2, i1 + 1, i2, 0);  // starting count over, move the first
        return Math.max(count, Math.max(c1, c2));
    }

    public int findLCSLengthTopDownDp(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        Integer[][][] dp = new Integer[s1.length()][s2.length()][maxLength];
        return findLCSLengthRecursiveTopDownDp(dp, s1, s2, 0, 0, 0);
    }

    private int findLCSLengthRecursiveTopDownDp(Integer[][][] dp, String s1, String s2, int i1, int i2, int count) {
        if (i1 == s1.length() || i2 == s2.length())
            return count;
        if (dp[i1][i2][count] == null) {
            int c1 = count;
            if (s1.charAt(i1) == s2.charAt(i2))
                c1 = findLCSLengthRecursiveTopDownDp(dp, s1, s2, i1 + 1, i2 + 1, count + 1);
            int c2 = findLCSLengthRecursiveTopDownDp(dp, s1, s2, i1, i2 + 1, 0);
            int c3 = findLCSLengthRecursiveTopDownDp(dp, s1, s2, i1 + 1, i2, 0);
            dp[i1][i2][count] = Math.max(c1, Math.max(c2, c3));
        }
        return dp[i1][i2][count];
    }

    public static void main(String[] args) {
        LongestCommonString lcs = new LongestCommonString();
        System.out.println(lcs.findLCSLength("abdca", "cbda"));
        System.out.println(lcs.findLCSLength("passport", "ppsspt"));

        System.out.println(lcs.findLCSLengthTopDownDp("abdca", "cbda"));
        System.out.println(lcs.findLCSLengthTopDownDp("passport", "ppsspt"));

    }
}
