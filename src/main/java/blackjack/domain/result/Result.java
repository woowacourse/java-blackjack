package blackjack.domain.result;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    Result(final String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return this.result;
    }
}
