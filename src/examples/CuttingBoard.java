package examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CuttingBoard {

    static class Result {

        public static int boardCutting(List<Integer> cost_y, List<Integer> cost_x) {
            // Write your code here
            long horizontalCuts = 1;
            long verticalCuts = 1;
            List<Integer[]> actions = new ArrayList<>();
            for (int j : cost_y) {
                actions.add(new Integer[]{0, j});
            }
            for (int costX : cost_x) {
                actions.add(new Integer[]{1, costX});
            }
            actions.sort(Comparator.comparing((Integer[] a) -> a[1]).reversed());

            long result = 0;
            for(Integer[] action: actions){
                if(action[0] == 0) {
                    result += verticalCuts * (long) action[1];
                    horizontalCuts++;
                }else {
                    result += horizontalCuts * (long) action[1];
                    verticalCuts++;
                }
            }

            return (int) (result % (Math.pow(10, 9) + 7));
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("a"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int m = Integer.parseInt(firstMultipleInput[0]);

                int n = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> cost_y = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> cost_x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                int result = Result.boardCutting(cost_y, cost_x);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

}
