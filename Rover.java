import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Rover {

    public static void calculateRoverPath(String[][] map) {

        int[][] power = new int[map.length][map[map.length - 1].length];

        Arrays.stream(power).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));


        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++) {
                if (Integer.parseInt(map[i][j]) < 0) {
                    // System.out.println("Data is not correct!");
                    return;
                }
                if (i == 0 & j == 0) {
                    power[i][j] = Integer.parseInt(map[i][j]);
                } else if (i == 0) {
                    power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j - 1], power[i][j]);
                } else if (j == 0) {
                    power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j], power[i][j]);
                } else {
                    power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j], power[i][j]);
                    power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j - 1], power[i][j]);

                    if (j != map[i].length - 1 && Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i - 1][j]) {
                        power[i - 1][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i - 1][j]);
                        i--;
                        j--;
                    }
                    if (Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i][j - 1]) {
                        power[i][j - 1] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i][j - 1]);
                        i--;
                        j--;
                    }
                }
            }

        int count = 0;
        int i = map.length - 1;
        int j = map[0].length - 1;
        int temp = power[i][j];

        String[] result = new String[map.length * map[0].length];
        result[count] = "[" + (i) + "][" + (j) + "]";

        do {
            if (i != 0) {
                if (temp - (Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1) == power[i - 1][j]) {
                    result[++count] = "[" + (i - 1) + "][" + j + "]";
                    temp = power[--i][j];
                    continue;
                }
            }
            if (j != 0) {
                if (temp - (Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1) == power[i][j - 1]) {
                    result[++count] = "[" + i + "][" + (j - 1) + "]";
                    temp = power[i][--j];
                    continue;
                }
            }
            if (i != map.length - 1) {
                if (temp - (Math.abs(Integer.parseInt(map[i + 1][j]) - Integer.parseInt(map[i][j])) + 1) == power[i + 1][j]) {
                    result[++count] = "[" + (i + 1) + "][" + j + "]";
                    temp = power[++i][j];
                    continue;
                }
            }
            if (j != map[i].length - 1) {
                if (temp - (Math.abs(Integer.parseInt(map[i][j + 1]) - Integer.parseInt(map[i][j])) + 1) == power[i][j + 1]) {
                    result[++count] = "[" + i + "][" + (j + 1) + "]";
                    temp = power[i][++j];
                }
            }
        } while (!(i == 0 && j == 0));

        try (FileWriter fileWriter = new FileWriter("path-plan.txt", false)) {
            for (int c = count; c >= 0; c--) {
                fileWriter.write(result[c]);
                if (c > 0) {
                    fileWriter.write("->");
                }
            }
            fileWriter.write("\nsteps: " + count);
            fileWriter.write("\nfuel: " + power[power.length - 1][power[0].length - 1]);

        } catch (IOException ex) {
            String message = ex.getMessage();
        }
    }
}

