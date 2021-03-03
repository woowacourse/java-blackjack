package blackjack.domain;

public enum ResultType {
    WIN("승"),
    LOSS("패"),
    DRAW("무승부");

    private final String result;

    ResultType(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}