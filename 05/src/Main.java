/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 03/12/21
    @copyright: Check the repository license.
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String []args) {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        List<String> list = new ArrayList<>();
        int ones, zeroes;
        long powerConsumption;

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
            stream.forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.get(0).length(); i++) {
            ones = zeroes = 0;

            for (String elem : list) {
                if (elem.charAt(i) == '0') {
                    zeroes ++;
                } else {
                    ones ++;
                }
            }

            if (ones > zeroes) {
                gamma.append(1);
                epsilon.append(0);
            } else {
                gamma.append(0);
                epsilon.append(1);
            }
        }

        powerConsumption = Long.parseLong(gamma.toString(), 2) * Long.parseLong(epsilon.toString(), 2);

        System.out.println("Result: " + powerConsumption);
    }
}
