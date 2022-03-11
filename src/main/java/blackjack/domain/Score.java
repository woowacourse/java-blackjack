package blackjack.domain;

public enum Score {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Score(String value) {
        this.value = value;
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
}
