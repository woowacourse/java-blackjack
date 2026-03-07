package domain.enums;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result getOpposite(Result result) {
        if (result.equals(WIN)) {
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
