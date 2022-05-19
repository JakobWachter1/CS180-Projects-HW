package PJ03;

import java.io.*;
import java.util.Scanner;

public class PlayGame {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, Welcome to Battleship!\nPlease enter a Game mode:");
        System.out.println("1. Automated\n2. Active");
        int gameModeInput = scanner.nextInt();
        File playerOnePos = new File("ShipPositionsPlayerOne.txt");
        File playerTwoPos = new File("ShipPositionsPlayerTwo.txt");
        FileReader fr1 = new FileReader(playerOnePos);
        FileReader fr2 = new FileReader(playerTwoPos);
        BufferedReader br1 = new BufferedReader(fr1);
        BufferedReader br2 = new BufferedReader(fr2);
        int[][] playerOneData = new int[10][14];
        int[][] playerTwoData = new int[10][14];

        for (int i = 0; i < 10; i++) {
            String lineOne = br1.readLine();
            for (int j = 0; j < 14; j++) {
                playerOneData[i][j] = (lineOne.charAt(j));
            }
        }
        br1.close();

        for (int i = 0; i < 10; i++) {
            String lineTwo = br2.readLine();
            for (int j = 0; j < 14; j++) {
                playerTwoData[i][j] = (lineTwo.charAt(j));
            }
        }
        br2.close();

        int playerOneHits = 0;
        int playerTwoHits = 0;
        int numTurns = 0;

        if (gameModeInput == 1) {
            System.out.print("Enter the filename with the game data:\n");
            scanner.nextLine();
            String gameDataInput = scanner.nextLine();
            FileReader fr3 = new FileReader(gameDataInput);
            LineNumberReader lr = new LineNumberReader(fr3);
            int lineCount = 0;
            while (lr.readLine() != null) {
                lineCount++;
            }
            lr.close();
            int[] gameDataArr = new int[lineCount];
            FileReader fr4 = new FileReader(gameDataInput);
            BufferedReader br3 = new BufferedReader(fr4);
            for (int i = 0; i < gameDataArr.length; i = i + 2) {
                String lineChar = br3.readLine();
                gameDataArr[i] = lineChar.charAt(0);
                String lineInt = br3.readLine();
                gameDataArr[i + 1] = Integer.parseInt((lineInt));
            }
            br3.close();

            int numTurns1 = 0;
            int numTurns2 = 0;

            for (int i = 0; i < gameDataArr.length; i = i + 2) {
                int tempColumn;
                int tempRow;
                gameDataArr[i] = gameDataArr[i] - 65;
                gameDataArr[i + 1] = gameDataArr[i + 1] - 1;
                tempColumn = gameDataArr[i + 1];
                tempRow = gameDataArr[i];
                if ((i % 4 == 0)) {
                    if (playerTwoData[tempRow][tempColumn] == 72) {
                        playerOneHits += 1;
                    }
                    numTurns1++;
                } else {
                    if (playerOneData[tempRow][tempColumn] == 72) {
                        playerTwoHits += 1;
                    }
                    numTurns2++;
                }
            }
            int winningPlayer;
            int numTurnsFinal;
            if (playerOneHits == 17) {
                winningPlayer = 1;
            } else {
                winningPlayer = 2;
            }
            int losingPlayerHits;
            if (winningPlayer == 1) {
                losingPlayerHits = playerTwoHits;
                numTurnsFinal = numTurns1;
            } else {
                losingPlayerHits = playerOneHits;
                numTurnsFinal = numTurns2;
            }



            int topHeavyCounterOne = 0;
            int middleHeavyCounterOne = 0;
            int bottomHeavyCounterOne = 0;

            int topHeavyCounterTwo = 0;
            int middleHeavyCounterTwo = 0;
            int bottomHeavyCounterTwo = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerOneData[i][j] == 72) {
                        topHeavyCounterOne += 1;
                    }
                }
            }
            for (int i = 3; i < 7; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerOneData[i][j] == 72) {
                        middleHeavyCounterOne += 1;
                    }
                }
            }
            for (int i = 7; i < 10; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerOneData[i][j] == 72) {
                        bottomHeavyCounterOne += 1;
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerTwoData[i][j] == 72) {
                        topHeavyCounterTwo += 1;
                    }
                }
            }
            for (int i = 3; i < 7; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerTwoData[i][j] == 72) {
                        middleHeavyCounterTwo += 1;
                    }
                }
            }
            for (int i = 7; i < 10; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerTwoData[i][j] == 72) {
                        bottomHeavyCounterTwo += 1;
                    }
                }
            }

            String boardPatternOne;
            String boardPatternTwo;

            if (topHeavyCounterOne >= 9) {
                boardPatternOne = "Top Heavy";
            } else if (middleHeavyCounterOne >= 9) {
                boardPatternOne = "Middle Heavy";
            } else if (bottomHeavyCounterOne >= 9) {
                boardPatternOne = "Bottom Heavy";
            } else {
                boardPatternOne = "Scattered";
            }

            if (topHeavyCounterTwo >= 9) {
                boardPatternTwo = "Top Heavy";
            } else if (middleHeavyCounterTwo >= 9) {
                boardPatternTwo = "Middle Heavy";
            } else if (bottomHeavyCounterTwo >= 9) {
                boardPatternTwo = "Bottom Heavy";
            } else {
                boardPatternTwo = "Scattered";
            }
            if (winningPlayer == 1) {
                System.out.print("Enemy fleet destroyed. Congratulations player 1!\n");
            } else {
                System.out.print("Enemy fleet destroyed. Congratulations player 2!\n");
            }

            GameLog gameLog = new GameLog(winningPlayer, losingPlayerHits, numTurnsFinal, boardPatternOne,
                    boardPatternTwo);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("GameLog.txt"));
                writer.write(gameLog.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            int numTurns1 = 1;
            int numTurns2 = 1;
            while (playerOneHits < 17 && playerTwoHits < 17) {
                numTurns += 1;
                if (numTurns % 2 == 1) {
                    System.out.print("Player 1 - Enter a row letter from A - J\n");
                    scanner.nextLine();
                    String lineChar = scanner.nextLine();
                    int tempRow = lineChar.charAt(0);
                    System.out.print("Player 1 - Enter a column number from 1 - 14\n");
                    int tempColumn = scanner.nextInt();
                    tempRow = tempRow - 65;
                    tempColumn = tempColumn - 1;
                    if (playerTwoData[tempRow][tempColumn] == 72) {
                        System.out.print("Value:H\n");
                        playerOneHits += 1;
                    } else {
                        System.out.print("Value:M\n");
                    }
                    numTurns1++;
                } else {
                    System.out.print("Player 2 - Enter a row letter from A - J\n");
                    scanner.nextLine();
                    String lineChar = scanner.nextLine();
                    int tempRow = lineChar.charAt(0);
                    System.out.print("Player 2 - Enter a column number from 1 - 14\n");
                    int tempColumn = scanner.nextInt();
                    tempRow = tempRow - 65;
                    tempColumn = tempColumn - 1;
                    if (playerOneData[tempRow][tempColumn] == 72) {
                        System.out.print("Value:H\n");
                        playerTwoHits += 1;
                    } else {
                        System.out.print("Value:M\n");
                    }
                    numTurns2++;
                }

            }
            int numTurnsFinal;

            int winningPlayer;
            if (playerOneHits == 17) {
                winningPlayer = 1;
            } else {
                winningPlayer = 2;
            }
            int losingPlayerHits;

            if (winningPlayer == 1) {
                losingPlayerHits = playerTwoHits;
                numTurnsFinal = numTurns1;
            } else {
                losingPlayerHits = playerOneHits;
                numTurnsFinal = numTurns2;
            }


            int topHeavyCounterOne = 0;
            int middleHeavyCounterOne = 0;
            int bottomHeavyCounterOne = 0;

            int topHeavyCounterTwo = 0;
            int middleHeavyCounterTwo = 0;
            int bottomHeavyCounterTwo = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerOneData[i][j] == 72) {
                        topHeavyCounterOne += 1;
                    }
                }
            }
            for (int i = 3; i < 7; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerOneData[i][j] == 72) {
                        middleHeavyCounterOne += 1;
                    }
                }
            }
            for (int i = 7; i < 10; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerOneData[i][j] == 72) {
                        bottomHeavyCounterOne += 1;
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerTwoData[i][j] == 72) {
                        topHeavyCounterTwo += 1;
                    }
                }
            }
            for (int i = 3; i < 7; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerTwoData[i][j] == 72) {
                        middleHeavyCounterTwo += 1;
                    }
                }
            }
            for (int i = 7; i < 10; i++) {
                for (int j = 0; j < 14; j++) {
                    if (playerTwoData[i][j] == 72) {
                        bottomHeavyCounterTwo += 1;
                    }
                }
            }

            String boardPatternOne;
            String boardPatternTwo;

            if (topHeavyCounterOne >= 9) {
                boardPatternOne = "Top Heavy";
            } else if (middleHeavyCounterOne >= 9) {
                boardPatternOne = "Middle Heavy";
            } else if (bottomHeavyCounterOne >= 9) {
                boardPatternOne = "Bottom Heavy";
            } else {
                boardPatternOne = "Scattered";
            }

            if (topHeavyCounterTwo >= 9) {
                boardPatternTwo = "Top Heavy";
            } else if (middleHeavyCounterTwo >= 9) {
                boardPatternTwo = "Middle Heavy";
            } else if (bottomHeavyCounterTwo >= 9) {
                boardPatternTwo = "Bottom Heavy";
            } else {
                boardPatternTwo = "Scattered";
            }
            if (winningPlayer == 1) {
                System.out.print("Enemy fleet destroyed. Congratulations player 1!\n");
            } else {
                System.out.print("Enemy fleet destroyed. Congratulations player 2!\n");
            }

            GameLog gameLog = new GameLog(winningPlayer, losingPlayerHits, numTurnsFinal, boardPatternOne,
                    boardPatternTwo);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("GameLog.txt"));
                writer.write(gameLog.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
