package examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ChocolatesInABox {


    private static int chocolateInBox(List<Integer> arr) {
        // If XOR of the chocolates in the boxes is 0 there is no way for the first to win
        //=> we need the XOR to be > 0
        int trs = arr.stream().reduce(0, (xor, cur) -> xor ^ cur);
        return (int) arr.stream().filter(a -> a > 0 && (a ^ trs) < a).count();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("f.txt")));
        bufferedReader.readLine();
        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = chocolateInBox(arr);
        System.out.println(result);
        bufferedReader.close();
    }

}
