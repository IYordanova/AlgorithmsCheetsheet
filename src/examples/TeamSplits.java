package examples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TeamSplits {

    private static class Team {
        int size;
        int lastSkillLevel;
    }

    private static int smallestTeam(List<Integer> contestants) {
        if (contestants.size() <= 1) {
            return contestants.size();
        }

        contestants.sort(Comparator.naturalOrder());

        List<Team> teams = new ArrayList<>();
        Team currentTeam = new Team();
        currentTeam.size = 1;
        currentTeam.lastSkillLevel = contestants.get(0);
        teams.add(currentTeam);

        for (int i = 1; i < contestants.size(); i++) {
            Integer currentSkillLevel = contestants.get(i);
            Optional<Team> potentialTeam = teams.stream()
                    .filter(t -> t.lastSkillLevel + 1 == currentSkillLevel)
                    .min(Comparator.comparingInt(o -> o.size));
            if (potentialTeam.isPresent()) {
                currentTeam = potentialTeam.get();
                currentTeam.size += 1;
                currentTeam.lastSkillLevel = currentSkillLevel;
            } else {
                currentTeam = new Team();
                currentTeam.size = 1;
                currentTeam.lastSkillLevel = currentSkillLevel;
                teams.add(currentTeam);
            }
        }
        return teams.stream().map(t -> t.size).min(Integer::compare).get();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("f.txt")));
        int cases = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> results = new ArrayList<>();
        IntStream.range(0, cases).forEach(qItr -> {
            try {
                String[] line = bufferedReader.readLine().split("\\s+");
                List<Integer> contestants = Arrays.stream(line)
                        .map(Integer::parseInt)
                        .skip(1)
                        .collect(toList());

                int result = smallestTeam(contestants);
                results.add(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bufferedReader.close();
        results.forEach(System.out::println);
    }

}
