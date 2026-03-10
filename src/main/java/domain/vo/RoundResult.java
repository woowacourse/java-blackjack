package domain.vo;

public enum RoundResult {

    WIN("승"),LOSE("패"),DRAW("무");

    private static final int BUST_CONDITION = 21;

    private final String result;

    RoundResult(String result) {
        this.result = result;
    }

    public static RoundResult judgeAgainst(int dealerScore, int playerScore) {
        if (playerScore > BUST_CONDITION) return RoundResult.LOSE;
        if (dealerScore > BUST_CONDITION) return RoundResult.WIN;
        if (playerScore > dealerScore) return RoundResult.WIN;
        if (playerScore < dealerScore) return RoundResult.LOSE;
        return RoundResult.DRAW;
    }

    public String result() {
        return result;
    }
}
