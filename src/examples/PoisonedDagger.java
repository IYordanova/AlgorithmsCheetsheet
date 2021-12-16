package examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PoisonedDagger {

    private static class TestCase {
        int dragonStrength;
        List<Integer> attackSeconds;
    }

    private static int calcMinK(TestCase testCase) {
        int attacksNum = testCase.attackSeconds.size();
        if (testCase.dragonStrength < attacksNum) {
            return 1;
        }
        int noLessThan = (int)Math.round((double)testCase.dragonStrength / attacksNum);
        int k = noLessThan;
        int lessThanSum = 0;
        int lastDiffIndex = -1;
        int lessThanCount = 0;
        for (int i = 0; i < attacksNum - 1; i++) {
            int diff = testCase.attackSeconds.get(i + 1) - testCase.attackSeconds.get(i);
            if (diff < noLessThan) {
                if (lastDiffIndex == i) {
                    lessThanSum += diff - 1;
                } else {
                    lessThanSum += diff;
                }
                lastDiffIndex = i + 1;
                lessThanCount++;
            }
        }
        if (lessThanSum != 0) {
            k = (testCase.dragonStrength - lessThanSum) / (attacksNum - lessThanCount);
        }
        return k;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("poisoned_dagger.txt").toPath());
        List<TestCase> testCases = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            String[] attackStrength = lines.get(i).split("\\s+");
            int strength = Integer.parseInt(attackStrength[1]);
            List<Integer> attackSeconds = Arrays.stream(lines.get(i + 1).split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            TestCase testCase = new TestCase();
            testCase.attackSeconds = attackSeconds;
            testCase.dragonStrength = strength;
            testCases.add(testCase);
        }

        testCases.stream()
                .map(PoisonedDagger::calcMinK)
                .forEach(System.out::println);
    }

}
