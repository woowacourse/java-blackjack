package blackjack.domain;

public enum Result {
    WIN("승"), LOSE("패"), DRAW("무");

    private String term;

    Result(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
}
