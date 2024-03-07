package domain.card;

public enum Symbol {

    CLUB("클로버"), DIAMOND("다이아몬드"), HEART("하트"), SPADE("스페이드");

    private final String displayName;

    Symbol(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
