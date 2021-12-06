/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 06/12/21
    @copyright: Check the repository license.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String []args) {
        String line;
        int x1, x2, y1, y2, start, end;
        Map<String, Integer> board = new HashMap<>();

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while ((line = br.readLine()) != null) {
                String[] explode = line.split(" -> ");
                String[] first, second;
                first = explode[0].split(",");
                second = explode[1].split(",");
                x1 = Integer.parseInt(first[0]);
                y1 = Integer.parseInt(first[1]);
                x2 = Integer.parseInt(second[0]);
                y2 = Integer.parseInt(second[1]);

                if (x1 == x2) {
                    // Horizontal
                    start = Math.min(y1, y2);
                    end = Math.max(y1, y2);
                    for (int i = start; i <= end; i++) {
                        String key = String.format("%d,%d", x1, i);

                        board.merge(key, 1, Integer::sum);
                    }
                } else if (y1 == y2) {
                    // Vertical
                    start = Math.min(x1, x2);
                    end = Math.max(x1, x2);
                    for (int i = start; i <= end; i++) {
                        String key = String.format("%d,%d", i, y1);

                        board.merge(key, 1, Integer::sum);
                    }
                } else {
                    // Diagonal
                    int xcoeff, ycoeff;
                    int distance = Math.max(Math.abs(x1-x2), Math.abs(y1-y2));

                    if (x1 > x2) {
                        xcoeff = -1;
                        if (y1 > y2) {
                            // N-W
                            ycoeff = -1;
                        } else {
                            // N-E
                            ycoeff = 1;
                        }
                    } else {
                        xcoeff = 1;
                        if (y1 > y2) {
                            // S-W
                            ycoeff = -1;
                        } else {
                            // S-E
                            ycoeff = 1;
                        }
                    }

                    for (int i = 0; i <= distance; i++) {
                        String key = String.format("%d,%d", x1 + xcoeff * i, y1 + ycoeff * i);

                        board.merge(key, 1, Integer::sum);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long res = board.values().stream()
                .filter(v -> v > 1)
                .count();

        System.out.println("Result: " + res);
    }
}
