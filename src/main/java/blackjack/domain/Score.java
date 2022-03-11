package blackjack.domain;

public enum Score {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Score(String value) {
        this.value = value;
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

    public static Score compete(int playerTotalNumber, int dealerTotalNumber) {
        int competeNumber = playerTotalNumber - dealerTotalNumber;

        if (competeNumber > 0) {
            return Score.WIN;
        }
        if (competeNumber == 0) {
            return Score.DRAW;
        }
        return Score.LOSE;
    }

    public String getValue(){
        return value;
    }
}
