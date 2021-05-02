import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Rover {

    public static void calculateRoverPath(int[][] map) {

        // try(FileWriter fileWriter = new FileWriter("path-plan.txt", false)){
        int[][] power = new int[map.length][map[map.length - 1].length];

        for (int[] ints : power) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] < 0) {
                    System.out.println("Data is not correct!");
                    return;
                }
                if (power[i][j] == Integer.MAX_VALUE) {
                    if (i == 0 & j == 0)
                        power[i][j] = map[i][j];
                    else if (i == 0) {
                        power[i][j] = Math.min(Math.abs(map[i][j - 1] - map[i][j]) + 1 + power[i][j - 1], power[i][j]);
                    } else if (j == 0) {
                        power[i][j] = Math.min(Math.abs(map[i - 1][j] - map[i][j]) + 1 + power[i - 1][j], power[i][j]);
                    } else {
                        power[i][j] = Math.min(Math.abs(map[i - 1][j] - map[i][j]) + 1 + power[i - 1][j], power[i][j]);
                        power[i][j] = Math.min(Math.abs(map[i][j - 1] - map[i][j]) + 1 + power[i][j - 1], power[i][j]);
                    }
                }
            }
        System.out.println(power[power.length - 1][power[0].length - 1] + " ");

        int count = 0;
        int i = map.length - 1;
        int j = map[0].length - 1;
        int temp = power[i][j];

        String[] result = new String[map.length * map[0].length];
        result[count] = "[" + (i) + "][" + (j) + "]";

        do {
            if (i != 0) {
                if (temp - (Math.abs(map[i - 1][j] - map[i][j]) + 1) == power[i - 1][j]) {
                    result[++count] = "[" + (i - 1) + "][" + j + "]";
                    temp = power[--i][j];
                    continue;
                }
            }
            if (j != 0) {
                if (temp - (Math.abs(map[i][j - 1] - map[i][j]) + 1) == power[i][j - 1]) {
                    result[++count] = "[" + i + "][" + (j - 1) + "]";
                    temp = power[i][--j];
                    continue;
                }
            }
            if (i != map.length - 1) {
                if (temp - (Math.abs(map[i + 1][j] - map[i][j]) + 1) == power[i + 1][j]) {
                    result[++count] = "[" + (i + 1) + "][" + j + "]";
                    temp = power[++i][j];
                    continue;
                }
            }
            if (j != map[i].length - 1) {
                if (temp - (Math.abs(map[i][j + 1] - map[i][j]) + 1) == power[i][j + 1]) {
                    result[++count] = "[" + i + "][" + (j + 1) + "]";
                    temp = power[i][++j];
                }
            }
        } while (!(i == 0 && j == 0));

        System.out.println("steps: " + count);
        for (int c = count; c >= 0; c--) {
            System.out.print(result[c]);
            if (c > 0) {
                System.out.print("->");
            }
        }
        System.out.println("");
    }

    // fileWriter.write("[0][0]->[1][0]->[1][1]");
    //fileWriter.append('\n');
     /*   }
        catch(IOException ex){
                System.out.println(ex.getMessage());
            }*/

    public static void main(String[] args) {
        System.out.println("5 12 17 9 12 15 14!!!!!!");

        int[][] map = {{0, 4}, {1, 3}};//5
        calculateRoverPath(map);
        int[][] map1 = {{0, 2, 3}, {6, 1, 4}, {5, 0, 8}}; //12
        calculateRoverPath(map1);
        int[][] map2 = {{0, 2, 3, 4, 1}, {2, 3, 4, 4, 1}, {3, 4, 5, 6, 2}, {4, 5, 6, 7, 1}, {6, 7, 8, 7, 1}};//17
        calculateRoverPath(map2);
        int[][] map3 = {{0, 1, 1, 1, 0}, {1, 1, 3, 1, 1}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};//9
        calculateRoverPath(map3);
        int[][] map4 = {{1, 1, 2, 3, 4}, {1, 0, 1, 2, 3}, {2, 1, 1, 1, 2}, {3, 3, 1, 0, 1}, {4, 3, 1, 1, 0}};//12
        calculateRoverPath(map4);
        int[][] map6 = {{3, 4, 4, 4, 4, 3}, {3, 2, 1, 1, 1, 4}, {4, 2, 1, 1, 3, 4}, {4, 4, 2, 2, 3, 4}};//14
        calculateRoverPath(map6);
        int[][] map5 = {{1, 1, 6, 7, 7}, {1, 1, 6, 7, 8}, {1, 6, 7, 8, 9}};//15
        calculateRoverPath(map5);
    }
}

