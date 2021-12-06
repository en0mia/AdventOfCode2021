/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 06/12/21
    @copyright: Check the repository license.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String []args) {
        String line;
        long []fishes = new long[9];

        for (int i = 0; i < 9; i++) {
            fishes[i] = 0;
        }

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            line = br.readLine();

            String []input = line.split(",");

            for (String i : input) {
                fishes[Integer.parseInt(i)] ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long zeroes;

        for (int i = 0; i < 256; i++) {
            zeroes = fishes[0];

            // Decrease
            System.arraycopy(fishes, 1, fishes, 0, fishes.length - 1);

            fishes[8] = zeroes;
            fishes[6] += zeroes;
        }

        System.out.println("Result: " + Arrays.stream(fishes).sum());
    }
}
