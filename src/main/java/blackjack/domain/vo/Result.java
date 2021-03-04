package blackjack.domain.vo;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int MAXIMUM_SCORE = 21;

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public static Result judge(int dealerScore, int playerScore) {
        if (isDealerWin(dealerScore, playerScore)) {
            return Result.LOSE;
        }
        if (isDraw(dealerScore, playerScore)) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    private static boolean isDealerWin(int dealerScore, int playerScore) {
        return playerScore > MAXIMUM_SCORE || (playerScore < dealerScore && dealerScore <= MAXIMUM_SCORE);
    }

    private static boolean isDraw(int dealerScore, int playerScore) {
        return playerScore == dealerScore && dealerScore <= MAXIMUM_SCORE;
    }

    public String getName() {
        return name;
    }
}
