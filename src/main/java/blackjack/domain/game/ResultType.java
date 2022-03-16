package blackjack.domain.game;

public enum ResultType {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String displayName;

    ResultType(final String displayName) {
        this.displayName = displayName;
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

    public static ResultType getOppositeOf(final ResultType targetType) {
        if (targetType == ResultType.WIN) {
            return ResultType.LOSE;
        }
        if (targetType == ResultType.LOSE) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }

    public String getDisplayName() {
        return displayName;
    }
}
