package domain.game;

public enum Result {
    WIN("승"),
    LOSE("패"),
    TIE("무");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result compareHandValue(int playerHandValue, int dealerHandValue) {
        if (playerHandValue > dealerHandValue) {
            return WIN;
        }
        return LOSE;
    }

    public static Result compareHandCount(int playerHandCount, int dealerHandCount) {
        if (playerHandCount > dealerHandCount) {
            return LOSE;
        }
        return WIN;
    }

    public String getResult() {
        return result;
    }
}
