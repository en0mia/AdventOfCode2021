/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 02/12/21
    @copyright: Check the repository license.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String []args) {
        int depth = 0;
        int horizontal = 0;
        int aim = 0;
        String line;

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                String action = split[0].toLowerCase(Locale.ROOT);
                int quantity = Integer.parseInt(split[1]);

                switch (action) {
                    case "forward" -> {
                        horizontal += quantity;
                        depth += (aim * quantity);
                    }
                    case "up" -> aim -= quantity;
                    case "down" -> aim += quantity;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Result: " + (depth * horizontal));
    }
}
