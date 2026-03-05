package domain.card;

public enum CardDenomination {

    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버")
    ;

    private final String denomination;

    CardDenomination(String denomination) {
        this.denomination = denomination;
    }

}
