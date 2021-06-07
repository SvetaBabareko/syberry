import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Rover {
    static int count = 0;
    static int powerResult = 0;

    public static String[] calculateRoverPath(String[][] map) {

        int[][] power = new int[map.length][map[map.length - 1].length];

        Arrays.stream(power).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));

        try {
            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[i].length; j++) {
                    boolean result = map[i][j].matches("-*\\d*|X");
                    if (!result) {
                        throw new CannotStartMovement();
                    }
                    if (map[i][j].equals("X")) {
                        continue;
                    }
                    if (i == 0 & j == 0) {
                        if (map[i][j].equals("X")) {
                            throw new CannotStartMovement();
                        }
                        power[i][j] = Math.abs(Integer.parseInt(map[i][j]));
                        continue;
                    }
                    if (j != 0 && power[i][j - 1] != Integer.MAX_VALUE) {
                        power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j - 1], power[i][j]);
                    }
                    if (i != 0 && j != 0 && power[i - 1][j - 1] != Integer.MAX_VALUE) {
                        power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j - 1], power[i][j]);
                    }
                    if (i != 0 && power[i - 1][j] != Integer.MAX_VALUE) {
                        power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j], power[i][j]);
                    }
                    if (i != 0 && j != map[i].length - 1 && power[i - 1][j + 1] != Integer.MAX_VALUE) {
                        power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j + 1], power[i][j]);
                    }

                    if (i != 0 && j != 0 && !map[i - 1][j - 1].equals("X") && power[i][j] != Integer.MAX_VALUE &&
                            Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i - 1][j - 1]) {
                        power[i - 1][j - 1] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i - 1][j - 1]);
                        i--;
                        j--;
                        continue;
                    }
                    if (i != 0 && !map[i - 1][j].equals("X") && power[i][j] != Integer.MAX_VALUE &&
                            Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i - 1][j]) {
                        power[i - 1][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i - 1][j]);
                        i--;
                        continue;
                    }
                    if (i != 0 && j != map[i].length - 1 && !map[i - 1][j + 1].equals("X") && power[i][j] != Integer.MAX_VALUE &&
                            Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i - 1][j + 1]) {
                        power[i - 1][j + 1] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i - 1][j + 1]);
                        i--;
                        continue;
                    }
                    if (j != 0 && !map[i][j - 1].equals("X") && power[i][j] != Integer.MAX_VALUE &&
                            Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i][j - 1]) {
                        power[i][j - 1] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i][j - 1]);
                        j--;
                    }


                }

        } catch (Exception |
                CannotStartMovement e) {
            try (FileWriter fileWriter = new FileWriter("path-plan.txt", false)) {
                fileWriter.write("Cannot start a movement because data is incorrect.");
            } catch (IOException ex) {
                String message = ex.getMessage();
            }
            System.exit(0);
        }

        count = 0;
        int i = map.length - 1;
        int j = map[0].length - 1;
        int temp = power[i][j];

        String[] result = new String[map.length * map[0].length];
        result[count] = "[" + (i) + "][" + (j) + "]";

        do {
            if (i != 0 && power[i - 1][j] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1) == power[i - 1][j]) {
                    result[++count] = "[" + (i - 1) + "][" + j + "]";
                    temp = power[--i][j];
                    continue;
                }
            }
            if (j != 0 && power[i][j - 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1) == power[i][j - 1]) {
                    result[++count] = "[" + i + "][" + (j - 1) + "]";
                    temp = power[i][--j];
                    continue;
                }
            }
            if (i != map.length - 1 && power[i + 1][j] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i + 1][j]) - Integer.parseInt(map[i][j])) + 1) == power[i + 1][j]) {
                    result[++count] = "[" + (i + 1) + "][" + j + "]";
                    temp = power[++i][j];
                    continue;
                }
            }
            if (j != map[i].length - 1 && power[i][j + 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i][j + 1]) - Integer.parseInt(map[i][j])) + 1) == power[i][j + 1]) {
                    result[++count] = "[" + i + "][" + (j + 1) + "]";
                    temp = power[i][++j];
                }
            }
            if (i != 0 && j != 0 && power[i - 1][j - 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1) == power[i - 1][j - 1]) {
                    result[++count] = "[" + (i - 1) + "][" + (j - 1) + "]";
                    temp = power[--i][--j];
                }
            }
            if (i != 0 && j != map[i].length - 1 && power[i - 1][j + 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1) == power[i - 1][j + 1]) {
                    result[++count] = "[" + (i - 1) + "][" + (j + 1) + "]";
                    temp = power[--i][++j];
                }
            }
            if (i != map.length - 1 && j != map[i].length - 1 && power[i + 1][j + 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i + 1][j + 1]) - Integer.parseInt(map[i][j])) + 1) == power[i + 1][j + 1]) {
                    result[++count] = "[" + (i + 1) + "][" + (j + 1) + "]";
                    temp = power[++i][++j];
                }
            }
            if (i != map.length - 1 && j != 0 && power[i + 1][j - 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i + 1][j - 1]) - Integer.parseInt(map[i][j])) + 1) == power[i + 1][j - 1]) {
                    result[++count] = "[" + (i + 1) + "][" + (j - 1) + "]";
                    temp = power[++i][--j];
                }
            }

        } while (!(i == 0 && j == 0));

        powerResult = power[power.length - 1][power[0].length - 1];
        return result;

    }


    private static void writeToFile(String[] result) {
        try (
                FileWriter fileWriter = new FileWriter("path-plan.txt", false)) {
            for (int c = count; c >= 0; c--) {
                fileWriter.write(result[c]);
                if (c > 0) {
                    fileWriter.write("->");
                }
            }
            fileWriter.write("\nsteps: " + count);
            fileWriter.write("\nfuel: " + powerResult);

        } catch (
                IOException ex) {
            String message = ex.getMessage();
        }

    }


    public static void main(String[] args) {

        String[][] map = {{"0", "X", "-10", "4", "4", "5", "6", "6"},
                {"1", "X", "-4", "4", "X", "-30", "-30", "7"},
                {"3", "-20", "4", "4", "4", "4", "-70", "8"},
                {"5", "X", "-40", "X", "-30", "5", "70", "9"},
                {"4", "4", "4", "4", "5", "6", "X", "10"}};

        writeToFile(calculateRoverPath(map));

    }
}


