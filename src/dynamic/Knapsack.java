package dynamic;

public class Knapsack {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex < 0 || currentIndex >= profits.length) {
            return 0;
        }
        int profit1 = 0;
        if (weights[currentIndex] <= capacity) {
            profit1 = profits[currentIndex] + knapsackRecursive(
                    profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);
        return Math.max(profit1, profit2);
    }

    public int solveKnapsackTopDownDp(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return knapsackRecursiveTopDownDp(dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursiveTopDownDp(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex < 0 || currentIndex >= profits.length)
            return 0;
        if (dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];
        int profit1 = 0;
        if (weights[currentIndex] <= capacity)
            profit1 = profits[currentIndex] + knapsackRecursiveTopDownDp(
                    dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        int profit2 = knapsackRecursiveTopDownDp(dp, profits, weights, capacity, currentIndex + 1);
        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

    public int solveKnapsackBottomUpDp(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length) {
            return 0;
        }
        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int c = 1; c <= capacity; c++) {
                int profit1 = 0, profit2 = 0;
                if (weights[i] <= c)
                    profit1 = profits[i] + dp[i][c - weights[i]];
                if (i > 0)
                    profit2 = dp[i - 1][c];
                dp[i][c] = Math.max(profit1, profit2);
            }
        }
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        Knapsack ks = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = ks.solveKnapsack(profits, weights, 7);
        int maxProfitDp = ks.solveKnapsackTopDownDp(profits, weights, 7);
        int maxProfitDp2 = ks.solveKnapsackBottomUpDp(profits, weights, 7);
        System.out.println(maxProfit);
    }

}
