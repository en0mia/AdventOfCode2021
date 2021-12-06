/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 06/12/21
    @copyright: Check the repository license.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String []args) {
        String []numbers = new String[0];
        String line;
        List<Board> boards = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        if (args.length != 1) {
            System.err.println("You should pass the path of the input file!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            numbers = br.readLine().trim().split(",");
            // Read the divider.
            br.readLine();

            while (true) {
                line = br.readLine();

                if (line == null) {
                    boards.add(new Board(sb.toString().trim()));
                    break;
                } else if (line.isBlank()) {
                    boards.add(new Board(sb.toString().trim()));
                    sb = new StringBuilder();
                    continue;
                }

                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int winnerBoards = 0;

        for (String number : numbers) {
            for (Board b : boards) {
                if (b.getWon()) {
                    continue;
                }

                Integer res = b.extract(Integer.parseInt(number));
                if (res != null) {
                    winnerBoards ++;
                    if (winnerBoards == boards.size()) {
                        System.out.println("Winner with sum: " + res);
                        return;
                    }
                }
            }
        }
    }
}
