/*
    @author: Simone Nicol <en0mia.dev@gmail.com>
    @created: 04/12/21
    @copyright: Check the repository license.
*/

public class Board {
    static class Element {
        int value;
        boolean checked;

        public Element(int value) {
            this.value = value;
            this.checked = false;
        }

        public int getValue() {
            return this.value;
        }

        public boolean isChecked() {
            return this.checked;
        }

        public void setChecked() {
            this.checked = true;
        }

        public String toString() {
            return this.value + ": " + (this.checked ? "Yes" : "No");
        }
    }

    private int uncheckedSum = 0;
    private final Element[][] matrix;

    public Board(String m) {
        this.matrix = new Element[5][5];
        this.parse(m);
    }

    public void parse(String m) {
        String[] numbers = m.split("\\s+");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                this.matrix[i][j] = new Element(Integer.parseInt(numbers[(i * 5) + j]));
                this.uncheckedSum += this.matrix[i][j].getValue();
            }
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                sb.append(this.matrix[i][j]).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public Integer extract(int n) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.matrix[i][j].getValue() == n) {
                    this.matrix[i][j].setChecked();
                    this.uncheckedSum -= n;
                }
            }
        }

        boolean winner;

        // Check rows.
        for (int i = 0; i < 5; i++) {
            winner = true;
            for (int j = 0; j < 5; j++) {
                if (!this.matrix[i][j].isChecked()) {
                    winner = false;
                    break;
                }
            }

            if (winner) {
                return n * this.uncheckedSum;
            }
        }

        // Check columns.
        for (int i = 0; i < 5; i++) {
            winner = true;
            for (int j = 0; j < 5; j++) {
                if (!this.matrix[j][i].isChecked()) {
                    winner = false;
                    break;
                }
            }

            if (winner) {
                return n * this.uncheckedSum;
            }
        }

        return null;
    }
}
