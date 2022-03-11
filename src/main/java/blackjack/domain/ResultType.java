package blackjack.domain;

public enum ResultType {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String value;

    ResultType(String value) {
        this.value = value;
    }

    public static ResultType findUserResult(int userScore, int dealerScore) {
        if (userScore > 21 && dealerScore > 21) {
            return DRAW;
        }
        if (userScore > 21) {
            return LOSE;
        }
        if (dealerScore > 21) {
            return WIN;
        }
        return compareScore(userScore, dealerScore);
    }

    private static ResultType compareScore(int myScore, int otherScore) {
        if (myScore > otherScore) {
            return WIN;
        }
        if (myScore < otherScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static ResultType findDealerResult(ResultType userResult) {
        if (userResult == WIN) {
            return LOSE;
        }
        if (userResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
