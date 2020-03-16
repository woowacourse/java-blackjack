package domain.result;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String korean;

    Result(final String resultKorean) {
        this.korean = resultKorean;
    }

    public Result getOpponentResult() {
        if (this == Result.WIN) {
            return Result.LOSE;
        }
        if (this == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    @Override
    public String toString() {
        return this.korean;
    }
}
