package blackjack.domain.game;

public enum ResultType {

    BLACKJACK_WIN("승", 1.5),
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    private final String displayName; // TODO: should be deleted
    private final double bettingYield;

    ResultType(final String displayName, double bettingYield) {
        this.displayName = displayName;
        this.bettingYield = bettingYield;
    }

    public static ResultType from(final Score score, final Score targetScore) {
        int compareResult = score.compareTo(targetScore);

        if (compareResult > 0) {
            return ResultType.WIN;
        }
        if (compareResult < 0) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }

    // TODO: should be deleted
    public static ResultType getOppositeOf(final ResultType targetType) {
        if (targetType == ResultType.WIN) {
            return ResultType.LOSE;
        }
        if (targetType == ResultType.LOSE) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }

    // TODO: should be deleted
    public String getDisplayName() {
        return displayName;
    }

    public double getBettingYield() {
        return bettingYield;
    }
}
