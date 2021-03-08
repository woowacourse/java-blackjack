package blackjack.domain;

public enum Outcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String word;

    Outcome(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
