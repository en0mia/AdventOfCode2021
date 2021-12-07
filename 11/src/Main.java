/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 07/12/21
    @copyright: Check the repository license.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String []args) {
        String line;
        String []inputs = new String[0];
        int minDistance = -1, minValue, maxValue;
        List<Integer> positions = new ArrayList<>();

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            line = br.readLine();

            if (line == null) {
                System.err.println("Error reading the file");
                return;
            }

            inputs = line.split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }

        minValue = Integer.parseInt(inputs[0]);
        maxValue = Integer.parseInt(inputs[0]);

        for (String i : inputs) {
            int tmp = Integer.parseInt(i);
            positions.add(tmp);
            if (tmp < minValue) {
                minValue = tmp;
            }
            if (tmp > maxValue) {
                maxValue = tmp;
            }
        }

        for (int i = minValue; i <= maxValue; i++) {
            int distance = 0;
            for (Integer p : positions) {
                distance += Math.abs(p - i);
            }

            if (distance < minDistance || minDistance == -1) {
                minDistance = distance;
            }
        }

        System.out.println("Result: " + minDistance);
    }
}
