package domain.card;

public enum CardSuitSymbol {
    SPACE("스페이스"),
    HEART("하트"),
    CLUB("클로버"),
    DIAMOND("다이아몬드");

    private final String suitSymbol;

    CardSuitSymbol(String suitSymbol) {
        this.suitSymbol = suitSymbol;
    }

    public String getSuitSymbol() {
        return this.suitSymbol;
    }
}

