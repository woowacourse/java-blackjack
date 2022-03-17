package blackjack.domain;

public enum Score {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1);

    private final String value;
    private final int profitRate;

    Score(String value, int profitRate) {
        this.value = value;
        this.profitRate = profitRate;
    }

    public static Score compare(int myNumber, int versusNumber) {
        if (myNumber < versusNumber) {
            return Score.LOSE;
        }
        if (myNumber > versusNumber) {
            return Score.WIN;
        }
        return Score.DRAW;
    }

    public static Score inverse(Score score) {
        if (score == WIN) {
            return LOSE;
        }
        if (score == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
    public int getProfitRate() {
        return profitRate;
    }
}
