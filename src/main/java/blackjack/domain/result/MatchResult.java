package blackjack.domain.result;

public enum MatchResult {
    WIN("승"), LOSE("패"), TIE("무");

    private final String name;

    MatchResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MatchResult reverse() {
        if (WIN == this) {
            return LOSE;
        }
        if (LOSE == this) {
            return WIN;
        }
        return this;
    }
}
