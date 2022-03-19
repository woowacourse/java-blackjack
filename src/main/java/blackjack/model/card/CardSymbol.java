package blackjack.model.card;

public enum CardSymbol {

    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버"),
    SPADE("스페이트"),
    ;

    private final String value;

    CardSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
