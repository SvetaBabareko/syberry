import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class Rover {

    public static void calculateRoverPath(int[][] map) {

        // try(FileWriter fileWriter = new FileWriter("path-plan.txt", false)){
        int dp[][] = new int[map.length][map[map.length - 1].length];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] < 0) {
                    System.out.println("Data is not correct!");
                    return;
                }

                if (dp[i][j] == Integer.MAX_VALUE) {
                    if (i == 0 & j == 0)
                        dp[i][j] = map[i][j];
                    else if (i == 0) {
                        dp[i][j] = Math.min(Math.abs(map[i][j - 1] - map[i][j]) + 1 + dp[i][j - 1], dp[i][j]);
                    } else if (j == 0) {
                        dp[i][j] = Math.min(Math.abs(map[i - 1][j] - map[i][j]) + 1 + dp[i - 1][j], dp[i][j]);
                    } else {
                        dp[i][j] = Math.min(Math.abs(map[i - 1][j] - map[i][j]) + 1 + dp[i - 1][j], dp[i][j]);
                        dp[i][j] = Math.min(Math.abs(map[i][j - 1] - map[i][j]) + 1 + dp[i][j - 1], dp[i][j]);
                    }
                }
            }
        System.out.println(dp[dp.length - 1][dp[0].length - 1] + " ");


        int count = 0;
        int i = map.length - 1;
        int j = map[0].length - 1;

        int temp = dp[i][j];

        String[] result = new String[map.length * map[0].length];
        result[count] = "[" + (map.length - 1) + "][" + (map[0].length - 1) + "]";

        // for (int i = map.length - 1; i >= 0; ) {
        //   for (int j = map[i].length - 1; j >= 0; )
        do {
            if (i != 0) {
                if (temp - (Math.abs(map[i - 1][j] - map[i][j]) + 1) == dp[i - 1][j]) {
                    result[++count] = "[" + (i - 1) + "][" + j + "]";
                    temp = dp[--i][j];
                    continue;
                }
            }
            if (j != 0) {
                if (temp - (Math.abs(map[i][j - 1] - map[i][j]) + 1) == dp[i][j - 1]) {
                    result[++count] = "[" + i + "][" + (j - 1) + "]";
                    temp = dp[i][--j];
                    continue;
                }
            }
            if (i != map.length - 1) {
                if (temp - (Math.abs(map[i + 1][j] - map[i + 1][j]) + 1) == dp[i + 1][j]) {
                    result[++count] = "[" + (i + 1) + "][" + j + "]";
                    temp = dp[++i][j];
                    continue;
                }
            }
            if (j != map[i].length - 1) {
                if (temp - (Math.abs(map[i][j + 1] - map[i][j + 1]) + 1) == dp[i][j + 1]) {
                    result[++count] = "[" + i + "][" + (j + 1) + "]";
                    temp = dp[i][++j];
                    continue;
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

    private static int[][] initMap() {
        //int[][] map = {{0, 4}, {1, 3}};//5
        // int [][] map = {{0,2,3},{6,1,4},{5,0,8}}; //12
       /* 0 2 3 4 1
        2 3 4 4 1
        3 4 5 6 2
        4 5 6 7 1
        6 7 8 7 1*/
        // int[][] map = {{0, 2, 3, 4, 1}, {2, 3, 4, 4, 1}, {3, 4, 5, 6, 2}, {4, 5, 6, 7, 1}, {6, 7, 8, 7, 1}};//17
        /*0 1 1 1 0
          1 1 3 1 1
          0 1 1 1 0
          0 0 0 0 0*/
        //int [][] map = {{0,1,1,1,0}, {1,1,3,1,1}, {0,1,1,1,0}, {0,0,0,0,0}};//9
        /*1 1 2 3 4
        1 0 1 2 3
        2 1 1 1 2
        3 3 1 0 1
        4 3 1 1 0*/
        // int [][] map = {{1,1,2,3,4}, {1,0,1,2,3},{2,1,1,1,2},{3,3,1,0,1},{4,3,1,1,0}};//12
        /*1 1 6 7 7
        1 1 6 7 8
        1 6 7 8 9*/
        //int [][] map = {{1,1,6,7,7}, {1,1,6,7,8},{1,6,7,8,9}};//15
        /*3 4 4 4 4 3
        3 2 1 1 1 4
        4 2 1 1 3 4
        4 4 2 2 3 4*/
        int[][] map = {{3, 4, 4, 4, 4, 3}, {3, 2, 1, 1, 1, 4}, {4, 2, 1, 1, 3, 4}, {4, 4, 2, 2, 3, 4}};//14

        return map;
    }

}

