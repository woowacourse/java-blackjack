package balckjack.domain;

public enum Result {
    LOSE("패"),
    DRAW("무"),
    WIN("승"),
    BLACKJACK("블랙잭");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
