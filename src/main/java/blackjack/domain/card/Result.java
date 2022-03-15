package blackjack.domain.card;

public enum Result {

    WIN("승리"),
    LOSE("패배"),
    TIE("무승부"),
    ;

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
