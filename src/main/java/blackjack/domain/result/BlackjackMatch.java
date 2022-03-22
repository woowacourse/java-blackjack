package blackjack.domain.result;

public enum BlackjackMatch {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    BlackjackMatch(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
