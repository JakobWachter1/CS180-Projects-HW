package PJ03;

public class GameLog {

    private int winningPlayer;
    private int losingPlayerHits;
    private int numTurns;
    private String boardPatternOne;
    private String boardPatternTwo;

    public GameLog(int winningPlayer, int losingPlayerHits, int numTurns, String boardPatternOne,
                   String boardPatternTwo) {
        this.winningPlayer = winningPlayer;
        this.losingPlayerHits = losingPlayerHits;
        this.numTurns = numTurns;
        this.boardPatternOne = boardPatternOne;
        this.boardPatternTwo = boardPatternTwo;
    }

    public String toString() {
        String strOne = "Battleship Game Log:\n";
        String strTwo = "Winning Player: Player " + winningPlayer + "\n";
        String strThree = "";
        if (winningPlayer == 1) {
            strThree = "Hits: 17 - " + losingPlayerHits + "\n";
        } else {
            strThree = "Hits: " + losingPlayerHits + " - 17\n";
        }
        String strFour = "Number of Turns To Win: " + numTurns + "\n";
        String strFive = "Player 1 Board Pattern: " + boardPatternOne + "\n";
        String strSix = "Player 2 Board Pattern: " + boardPatternTwo + "\n";
        return strOne + strTwo + strThree + strFour + strFive + strSix;
    }
}
