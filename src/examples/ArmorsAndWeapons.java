package examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArmorsAndWeapons {

    private static class SynergyPair {
        final int armor;
        final int weapon;

        private SynergyPair(int armor, int weapon) {
            this.armor = armor;
            this.weapon = weapon;
        }
    }

    private static class TestCase {
        int armors;
        int weapons;
        List<SynergyPair> synergyPairs;
    }

    private static int minTimeToLastArmorAndWeapon(TestCase testCase, int armor, int weapon, int hours, int increase) {
        if (armor >= testCase.armors && weapon >= testCase.weapons) {
            return hours - 1;
        }

        List<Integer> result = new ArrayList<>();
        Optional<SynergyPair> synergyPair = testCase.synergyPairs.stream()
                .filter(pair ->  pair.armor == armor && pair.weapon == weapon)
                .findFirst();

        int sum = synergyPair.map(pair -> pair.armor + pair.weapon + 1).orElseGet(() -> armor + weapon);
        if (increase == 0) {
            System.out.println("choosing " + sum + " " + weapon);
            result.add(minTimeToLastArmorAndWeapon(testCase, sum, weapon, hours + 1, 1));
        } else if (increase == 1) {
            System.out.println("choosing " + armor + " " + sum);
            result.add(minTimeToLastArmorAndWeapon(testCase, armor, sum, hours + 1, 0));
        } else {
            System.out.println("  choosing " + sum + " " + weapon);
            result.add(minTimeToLastArmorAndWeapon(testCase, sum, weapon, hours + 1, 1));
            System.out.println("  choosing " + armor + " " + sum);
            result.add(minTimeToLastArmorAndWeapon(testCase, armor, sum, hours + 1, 0));
        }

        return result.stream().min(Integer::compare).get();
    }

    private static int minTimeToLastArmorAndWeapon(TestCase testCase) {
        return minTimeToLastArmorAndWeapon(testCase, 1, 1, 1, -1);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("armors_weapons.txt").toPath());
        List<TestCase> testCases = new ArrayList<>();
        for (int i = 1; i < lines.size(); ) {
            TestCase t = new TestCase();
            String[] nm = lines.get(i).split("\\s+");
            t.armors = Integer.parseInt(nm[0]);
            t.weapons = Integer.parseInt(nm[1]);
            t.synergyPairs = new ArrayList<>();
            int synergyPairNums = Integer.parseInt(lines.get(i + 1));
            for (int j = 0; j < synergyPairNums; j++) {
                String[] pair = lines.get(i + 2 + j).split("\\s+");
                t.synergyPairs.add(new SynergyPair(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
            }
            testCases.add(t);
            i = i + synergyPairNums + 2;
        }

        testCases.stream()
                .map(ArmorsAndWeapons::minTimeToLastArmorAndWeapon)
                .forEach(System.out::println);
    }

}
