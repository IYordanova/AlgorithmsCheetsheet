package examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrazyRobot {

    private final static char LAB_MARKER = 'L';
    private final static char WALL_MARKER = '#';
    private final static char VALID_PATH_MARKER = '+';

    private static class LabPoint {
        final int row;
        final int col;

        public LabPoint(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class TestCase {
        char[][] grid;
        LabPoint labPoint;
    }

    private static boolean inRange(char[][] grid, int row, int col) {
        return row > -1 && col > -1 && row < grid.length && col < grid[0].length;
    }

    private static boolean noTop(char[][] grid, int row, int col) {
        return row == 0 || grid[row - 1][col] == WALL_MARKER;
    }

    private static boolean noBottom(char[][] grid, int row, int col) {
        return row == grid.length - 1 || grid[row + 1][col] == WALL_MARKER;
    }

    private static boolean noRight(char[][] grid, int row, int col) {
        return col == grid[0].length - 1 || grid[row][col + 1] == WALL_MARKER;
    }

    private static boolean noLeft(char[][] grid, int row, int col) {
        return col == 0 || grid[row][col - 1] == WALL_MARKER;
    }

    private static void markPath(char[][] grid, int row, int col) {
        if (!inRange(grid, row, col)) {
            return;
        }

        char current = grid[row][col];
        if (current == WALL_MARKER || grid[row][col] == VALID_PATH_MARKER) {
            return;
        }

        boolean noTop = noTop(grid, row, col);
        boolean noBottom = noBottom(grid, row, col);
        boolean noRight = noRight(grid, row, col);
        boolean noLeft = noLeft(grid, row, col);

        long blockedCellsCount = Stream.of(noLeft, noRight, noTop, noBottom)
                .filter(i -> i)
                .count();
        if (blockedCellsCount == 2 || blockedCellsCount == 3) {
            if (current != LAB_MARKER) {
                grid[row][col] = VALID_PATH_MARKER;
            }
                markPath(grid, row, col - 1);
                markPath(grid, row, col + 1);
                markPath(grid, row - 1, col);
                markPath(grid, row + 1, col);
        }
        if (current == LAB_MARKER) {
            markPath(grid, row - 1, col);
            markPath(grid, row + 1, col);
            markPath(grid, row, col - 1);
            markPath(grid, row, col + 1);
        }
    }

    private static String markPath(TestCase testCase) {
        markPath(testCase.grid, testCase.labPoint.row, testCase.labPoint.col);
        return Arrays.stream(testCase.grid).sequential()
                .map(i -> Stream.of(i)
                        .map(String::valueOf)
                        .collect(Collectors.joining("")))
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("robot_lab.txt").toPath());
        List<TestCase> testCases = new ArrayList<>();
        for (int i = 1; i < lines.size(); ) {
            String[] gridSize = lines.get(i).split("\\s+");
            int rows = Integer.parseInt(gridSize[0]);
            int cols = Integer.parseInt(gridSize[1]);

            char[][] grid = new char[rows][cols];
            TestCase testCase = new TestCase();
            for (int r = 0; r < rows; r++) {
                char[] rowMarkers = lines.get(i + r + 1).toCharArray();
                for (int c = 0; c < cols; c++) {
                    if (rowMarkers[c] == LAB_MARKER) {
                        testCase.labPoint = new LabPoint(r, c);
                    }
                    grid[r][c] = rowMarkers[c];
                }
            }
            testCase.grid = grid;
            testCases.add(testCase);
            i = i + rows + 1;
        }

        testCases.stream()
                .map(CrazyRobot::markPath)
                .forEach(System.out::println);
    }

}
