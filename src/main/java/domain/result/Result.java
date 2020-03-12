package domain.result;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String korean;

    Result(final String resultKorean) {
        this.korean = resultKorean;
    }

    @Override
    public String toString() {
        return this.korean;
    }
}
