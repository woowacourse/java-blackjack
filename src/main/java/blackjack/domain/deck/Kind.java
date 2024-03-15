package blackjack.domain.deck;

public enum Kind {

    SPADE("스페이드"),
    DIAMOND("다이아몬드"),
    HEART("하트"),
    CLOVER("클로버");

    private final String kindName;

    Kind(String kindName) {
        this.kindName = kindName;
    }

    public String getKindName() {
        return kindName;
    }
}
