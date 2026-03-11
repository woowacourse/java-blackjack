package domain.enums;

public enum Result {

    WIN("승"),
    WIN_BLACKJACK("블랙잭 승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result getOpposite(Result result) {
        if (result.equals(WIN) || result.equals(WIN_BLACKJACK)) {
            return LOSE;
        }

        if (result.equals(LOSE)) {
            return WIN;
        }

        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
