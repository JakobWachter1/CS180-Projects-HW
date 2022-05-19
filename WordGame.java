package HW07;

import java.util.Scanner;

public class WordGame {

    public static String welcome = "Ready to play?";
    public static String yesNo = "1.Yes\n2.No";
    public static String noPlay = "Maybe next time!";
    public static String currentRoundLabel = "Current Round: ";
    public static String enterGuess = "Please enter a guess!";
    public static String winner = "You got the answer!";
    public static String outOfGuesses = "You ran out of guesses!";
    public static String solutionLabel = "Solution: ";
    public static String incorrect = "That's not it!";
    public static String keepPlaying = "Would you like to make another guess?";
    public static String enterWords = "Please enter a comma-separated list of words";
    public static String enterSeed = "Please enter a seed for the random number generator";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s\n", enterWords);
        String words = scanner.nextLine();
        String[] wordsArray = words.split(",");
        System.out.printf("%s\n", enterSeed);
        int seed = scanner.nextInt();
        WordLibrary wordLibrary = new WordLibrary(wordsArray, seed);
        wordLibrary.cleanLibrary();
        System.out.printf("%s\n", welcome);
        System.out.printf("%s\n", yesNo);
        int menuOneInput = scanner.nextInt();
        if (menuOneInput == 2) {
            System.out.printf("%s\n", noPlay);
        } else {
            while (menuOneInput != 2) {
                String solution = wordLibrary.chooseWord();
                WordGuesser wordGuesser = new WordGuesser(solution);
                int round = 0;
                int makeGuess = 1;
                boolean wordGuessWin = false;
                int numGuesses = 0;
                while ((!wordGuessWin) && (numGuesses < 5) && (makeGuess == 1)) {
                    wordGuesser.setRound(round);
                    wordGuesser.setSolution(solution);
                    System.out.printf("%s", currentRoundLabel);
                    System.out.printf("%d\n", round);
                    round += 1;
                    wordGuesser.printField();
                    System.out.printf("%s\n", enterGuess);
                    scanner.nextLine();
                    String guess = scanner.nextLine();
                    numGuesses += 1;
                    wordGuesser.checkGuess(guess);
                    if (wordGuesser.checkGuess(guess)) {
                        System.out.printf("%s\n", winner);
                        wordGuesser.printField();
                        wordGuessWin = true;
                    } else if (numGuesses == 5) {
                        System.out.printf("%s\n", outOfGuesses);
                        System.out.printf("%s", solutionLabel);
                        System.out.printf("%s\n", solution);
                    } else {
                        System.out.printf("%s\n", incorrect);
                        System.out.printf("%s\n", keepPlaying);
                        System.out.printf("%s\n", yesNo);
                        makeGuess = scanner.nextInt();
                    }


                }
                System.out.printf("%s\n", welcome);
                System.out.printf("%s\n", yesNo);
                menuOneInput = scanner.nextInt();
            }
            System.out.printf("%s\n", noPlay);
        }
    }
}

