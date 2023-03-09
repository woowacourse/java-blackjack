package domain.game;

public enum Result {
    WIN("승"),
    LOSE("패"),
    TIE("무");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result calculateResult(int handValueGap, int handCountGap) {
        if (handValueGap > 0) {
            return WIN;
        }
        if (handValueGap < 0) {
            return LOSE;
        }
        if (handCountGap > 0) {
            return LOSE;
        }
        if (handCountGap < 0) {
            return WIN;
        }
        return TIE;
    }

    public String getResult() {
        return result;
    }
}
