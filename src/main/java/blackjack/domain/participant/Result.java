package blackjack.domain.participant;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
