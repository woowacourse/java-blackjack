package blackjack.domain.result;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private String result;

    Result(String result) {
        this.result = result;
    }
}
