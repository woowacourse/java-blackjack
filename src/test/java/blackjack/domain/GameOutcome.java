package blackjack.domain;

public enum GameOutcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String koreanSymbol;

    GameOutcome(final String koreanSymbol) {
        this.koreanSymbol = koreanSymbol;
    }
}
