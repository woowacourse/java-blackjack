package blackjack.domain;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    ResultType(String result) {
        this.result = result;
    }

    public String getName() {
        return this.result;
    }
}
