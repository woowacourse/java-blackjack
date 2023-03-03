package blackjack.model.result;

public enum Result {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
