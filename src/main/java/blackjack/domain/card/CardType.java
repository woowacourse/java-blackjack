package blackjack.domain.card;

public enum CardType {

    SPADE("스페이드"),
    CLOVER("클로버"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    ;

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
