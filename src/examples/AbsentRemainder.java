package examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbsentRemainder {

    private static String calcPairs(List<Integer> nums) {
        int targetPairCount = nums.size() / 2;
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                int x = nums.get(i);
                int y = nums.get(j);

                if (!nums.contains(x % y)) {
                    pairs.add(x + " " + y);
                }
                if (!nums.contains(y % x)) {
                    pairs.add(y + " " + x);
                }
            }
//            if (pairs.size() >= targetPairCount) {
//                break;
//            }
        }
        return String.join("\n", pairs);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("absent_remainder.txt").toPath());
        List<List<Integer>> testCases = new ArrayList<>();
        for (int i = 2; i < lines.size(); i += 2) {
            List<Integer> nums = Arrays.stream(lines.get(i).split("\\s+"))
                    .map(Integer::parseInt)
//                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            testCases.add(nums);
        }

        testCases.stream()
                .map(AbsentRemainder::calcPairs)
                .forEach(System.out::println);
    }

}
