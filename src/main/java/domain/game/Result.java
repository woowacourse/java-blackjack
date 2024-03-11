package domain.game;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public Result reverse() {
        return switch (this) {
            case WIN -> LOSE;
            case LOSE -> WIN;
            case DRAW -> DRAW;
        };
    }

    public String getResult() {
        return result;
    }

    public static Result compare(int current, int opponent) {
        if (current > opponent) {
            return WIN;
        }
        if (current < opponent) {
            return LOSE;
        }
        return DRAW;
    }
}
