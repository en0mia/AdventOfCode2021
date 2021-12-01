/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 01/12/21
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
        List<Integer> list = new ArrayList<>();
        int increases = 0;
        int tmpSum;
        List<Integer> clustered = new ArrayList<>();

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {
            stream.forEach(value -> list.add(Integer.parseInt(value)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size() - 2; i++) {
            tmpSum = 0;

            for (int j = 0; j < 3; j++) {
                tmpSum += list.get(i+j);
            }

            clustered.add(tmpSum);
        }

        for (int i = 1; i < clustered.size(); i++) {
            if (clustered.get(i) > clustered.get(i-1)) {
                increases ++;
            }
        }

        System.out.println(increases + " increases!");
    }
}
