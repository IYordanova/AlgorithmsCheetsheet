package dynamic;

public class Fibonacci {

    public int calculateFibonacci(int n) {
        if (n < 2) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }

    public int calculateFibonacciBottomUpDp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int calculateFibonacciBottomUpDpSpace(int n) {
        int n1 = 0, n2 = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        System.out.println(fib.calculateFibonacci(5));
        System.out.println(fib.calculateFibonacci(6));

        System.out.println(fib.calculateFibonacciBottomUpDp(5));
        System.out.println(fib.calculateFibonacciBottomUpDp(6));

        System.out.println(fib.calculateFibonacciBottomUpDpSpace(5));
        System.out.println(fib.calculateFibonacciBottomUpDpSpace(6));
    }
}
