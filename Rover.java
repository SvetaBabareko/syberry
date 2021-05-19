import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Rover {

   
    public static void calculateRoverPath(String[][] map) {

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
                    } else if ((i == 0 || power[i - 1][j] == Integer.MAX_VALUE) && power[i][j - 1] != Integer.MAX_VALUE) {
                        power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j - 1], power[i][j]);
                        if (i!=0 && power[i - 1][j] == Integer.MAX_VALUE && j!=0 && power[i - 1][j - 1] != Integer.MAX_VALUE) {
                            power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j - 1], power[i][j]);
                        }
                    } else if ((j == 0 || power[i][j - 1] == Integer.MAX_VALUE) && power[i - 1][j] != Integer.MAX_VALUE) {
                        power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j], power[i][j]);
                        if (power[i - 1][j + 1] != Integer.MAX_VALUE && j != map[i].length - 1) {
                            power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j + 1], power[i][j]);
                        }
                    } else {
                        if (power[i - 1][j] != Integer.MAX_VALUE) {
                            power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j], power[i][j]);
                        }
                        if (power[i][j - 1] != Integer.MAX_VALUE) {
                            power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j - 1], power[i][j]);
                        }
                        if (power[i - 1][j - 1] != Integer.MAX_VALUE) {
                            power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j - 1], power[i][j]);
                        }
                        if (j != map[i].length - 1 && power[i - 1][j + 1] != Integer.MAX_VALUE) {
                            power[i][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1 + power[i - 1][j + 1], power[i][j]);
                        }

                        if ((j != map[i].length - 1)
                                && Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i - 1][j]) {
                            power[i - 1][j] = Math.min(Math.abs(Integer.parseInt(map[i - 1][j]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i - 1][j]);
                            i--;
                            j--;
                        }
                        if (power[i][j - 1] != Integer.MAX_VALUE &&
                                Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j] < power[i][j - 1]) {
                            power[i][j - 1] = Math.min(Math.abs(Integer.parseInt(map[i][j - 1]) - Integer.parseInt(map[i][j])) + 1 + power[i][j], power[i][j - 1]);
                            i--;
                            j--;
                        }
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

        int count = 0;
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
            if (i != 0 && j != 0 && power[i - 1][j-1] != Integer.MAX_VALUE ) {
                if (temp - (Math.abs(Integer.parseInt(map[i - 1][j - 1]) - Integer.parseInt(map[i][j])) + 1) == power[i - 1][j - 1]) {
                    result[++count] = "[" + (i - 1) + "][" + (j - 1) + "]";
                    temp = power[--i][--j];
                }
            }
            if (i != 0 && j != map[i].length - 1 && power[i-1][j + 1] != Integer.MAX_VALUE) {
                if (temp - (Math.abs(Integer.parseInt(map[i - 1][j + 1]) - Integer.parseInt(map[i][j])) + 1) == power[i - 1][j + 1]) {
                    result[++count] = "[" + (i - 1) + "][" + (j + 1) + "]";
                    temp = power[--i][++j];
                }
            }
            if (i != map.length - 1 &&  j != map[i].length - 1 && power[i + 1][j+1] != Integer.MAX_VALUE ) {
                if (temp - (Math.abs(Integer.parseInt(map[i + 1][j + 1]) - Integer.parseInt(map[i][j])) + 1) == power[i + 1][j + 1]) {
                    result[++count] = "[" + (i + 1) + "][" + (j + 1) + "]";
                    temp = power[++i][++j];
                }
            }
            if (i != map.length - 1 &&  j !=0 && power[i + 1][j-1] != Integer.MAX_VALUE ) {
                if (temp - (Math.abs(Integer.parseInt(map[i + 1][j - 1]) - Integer.parseInt(map[i][j])) + 1) == power[i + 1][j - 1]) {
                    result[++count] = "[" + (i + 1) + "][" + (j - 1) + "]";
                    temp = power[++i][--j];
                }
            }

        } while (!(i == 0 && j == 0));

        try (
                FileWriter fileWriter = new FileWriter("path-plan.txt", false)) {
            for (int c = count; c >= 0; c--) {
                fileWriter.write(result[c]);
                if (c > 0) {
                    fileWriter.write("->");
                }
            }
            fileWriter.write("\nsteps: " + count);
            fileWriter.write("\nfuel: " + power[power.length - 1][power[0].length - 1]);

        } catch (
                IOException ex) {
            String message = ex.getMessage();
        }
    }


    private static class CannotStartMovement extends Throwable {
    }
}

