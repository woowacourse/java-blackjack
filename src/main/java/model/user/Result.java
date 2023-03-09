package model.user;

public enum Result {

    WIN("승"),
    TIE("무승부"),
    LOSE("패");

    public static final int BUST_NUMBER = 21;

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Result judge(final int dealerTotalValue, final int playerTotalValue) {
        if (dealerTotalValue > BUST_NUMBER || playerTotalValue > BUST_NUMBER) {
            return judgeOverBurst(dealerTotalValue, playerTotalValue);
        }

        return judgeBelowBurst(dealerTotalValue, playerTotalValue);
    }

    private static Result judgeOverBurst(final int dealerTotalValue, final int userTotalValue) {
        if (userTotalValue > BUST_NUMBER && dealerTotalValue > BUST_NUMBER) {
            return LOSE;
        }

        return userTotalValue > BUST_NUMBER ? LOSE : WIN;
    }

    private static Result judgeBelowBurst(final int dealerTotalValue, final int userTotalValue) {
        if (dealerTotalValue == userTotalValue) {
            return TIE;
        }
        return dealerTotalValue < userTotalValue ? WIN : LOSE;
    }
}
