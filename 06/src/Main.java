/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 03/12/21
    @copyright: Check the repository license.
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Main {
    public static void main(String []args) {
        List<String> list = new ArrayList<>();
        int length;
        long oxygenValue, co2Value;

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
            stream.forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        length = list.get(0).length();

        Set<String> oxygen = new HashSet<>(list);
        Set<String> co2 = new HashSet<>(list);

        for (int i = 0; i < length; i++) {
            if (oxygen.size() == 1) {
                break;
            }

            oxygen.removeAll(toRemove(oxygen, i, true));
        }

        for (int i = 0; i < length; i++) {
            if (co2.size() == 1) {
                break;
            }

            co2.removeAll(toRemove(co2, i, false));
        }

        if (oxygen.isEmpty() || co2.isEmpty()) {
            System.err.println("Input error!");
            return;
        }

        oxygenValue = Long.parseLong(getFirst(oxygen), 2);
        co2Value = Long.parseLong(getFirst(co2), 2);

        System.out.println("Result: " + (oxygenValue * co2Value));
    }

    public static Set<String> toRemove(Set<String> input, int position, boolean common) {
        Set<String> onesSet = new HashSet<>();
        Set<String> zeroesSet = new HashSet<>();
        int ones = 0, zeroes = 0;

        for (String elem : input) {
            if (elem.charAt(position) == '0') {
                zeroesSet.add(elem);
                zeroes ++;
            } else {
                onesSet.add(elem);
                ones ++;
            }
        }

        if (ones >= zeroes) {
            if (common) {
                return onesSet;
            }

            return zeroesSet;
        } else {
            if (common) {
                return zeroesSet;
            }

            return onesSet;
        }
    }

    public static String getFirst(Set<String> input) {
        for (String elem : input) {
            return elem;
        }

        return "0";
    }
}
