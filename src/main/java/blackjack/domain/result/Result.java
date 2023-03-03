package blackjack.domain.result;

public enum Result {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String playerResult;

    Result(String playerResult) {
        this.playerResult = playerResult;
    }

    public static Result calculateResult(int playerScore, int dealerScore) {
        if(playerScore>21){
            playerScore =0;
        }
        if(dealerScore>21){
            dealerScore =0;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        return TIE;
    }

    public Result ofOppositeResult() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return TIE;
    }

    public String getResult() {
        return this.playerResult;
    }
}
