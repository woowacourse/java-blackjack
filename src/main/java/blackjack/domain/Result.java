package blackjack.domain;

public enum Result {

    TWENTY_ONE("21"),
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getName() {
        return this.result;
    }
}
