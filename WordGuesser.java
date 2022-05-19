package HW07;

public class WordGuesser {
    private String[][] playingField;
    private int round;
    private String solution;

    public WordGuesser(String solution) {
        this.solution = solution;
        round = 0;
        this.playingField = new String[5][5];
        for (int i = 0; i < playingField.length; i++) {
            for (int k = 0; k < playingField.length; k++) {
                playingField[i][k] = " ";
            }
        }
    }

    public String[][] getPlayingField() {
        return playingField;
    }

    public int getRound() {
        return round;
    }

    public String getSolution() {
        return solution;
    }

    public void setPlayingField(String[][] playingField) {
        this.playingField = playingField;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean checkGuess(String guess) {
        if (guess.equals(solution)) {
            for (int i = 0; i <= 4; i++) {
                if (solution.charAt(i) == guess.charAt(i)) {
                    playingField[round][i] = "'" + guess.charAt(i) + "'";
                }
            }
            return true;
        }
        else {
            for (int i = 0; i <= 4; i++) {
                if (solution.charAt(i) == guess.charAt(i)) {
                    playingField[round][i] = "'" + guess.charAt(i) + "'";
                }
                else if ((solution.contains(guess.substring(i, i + 1)))) {
                    playingField[round][i] = "*" + guess.charAt(i) + "*";
                }
                else {
                    playingField[round][i] = "{" + guess.charAt(i) + "}";
                }
            }
            return false;
        }
    }

    public void printField() {
        for (int y = 0; y < 5; y++) {
            for (int i = 0; i < 5; i++) {
                if (i < 4) {
                    System.out.printf("%s | ", playingField[y][i]);
                } else {
                    System.out.printf("%s\n", playingField[y][i]);
                }
            }
        }
    }

}

