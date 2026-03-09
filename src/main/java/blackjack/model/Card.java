package blackjack.model;

public enum Card {
    A_SPADE(1, "A스페이드"),
    TWO_SPADE(2, "2스페이드"),
    THREE_SPADE(3, "3스페이드"),
    FOUR_SPADE(4, "4스페이드"),
    FIVE_SPADE(5, "5스페이드"),
    SIX_SPADE(6, "6스페이드"),
    SEVEN_SPADE(7, "7스페이드"),
    EIGHT_SPADE(8, "8스페이드"),
    NINE_SPADE(9, "9스페이드"),
    TEN_SPADE(10, "10스페이드"),
    J_SPADE(10, "J스페이드"),
    Q_SPADE(10, "Q스페이드"),
    K_SPADE(10, "K스페이드"),

    A_HEART(1, "A하트"),
    TWO_HEART(2, "2하트"),
    THREE_HEART(3, "3하트"),
    FOUR_HEART(4, "4하트"),
    FIVE_HEART(5, "5하트"),
    SIX_HEART(6, "6하트"),
    SEVEN_HEART(7, "7하트"),
    EIGHT_HEART(8, "8하트"),
    NINE_HEART(9, "9하트"),
    TEN_HEART(10, "10하트"),
    J_HEART(10, "J하트"),
    Q_HEART(10, "Q하트"),
    K_HEART(10, "K하트"),

    A_CLOVER(1, "A클로버"),
    TWO_CLOVER(2, "2클로버"),
    THREE_CLOVER(3, "3클로버"),
    FOUR_CLOVER(4, "4클로버"),
    FIVE_CLOVER(5, "5클로버"),
    SIX_CLOVER(6, "6클로버"),
    SEVEN_CLOVER(7, "7클로버"),
    EIGHT_CLOVER(8, "8클로버"),
    NINE_CLOVER(9, "9클로버"),
    TEN_CLOVER(10, "10클로버"),
    J_CLOVER(10, "J클로버"),
    Q_CLOVER(10, "Q클로버"),
    K_CLOVER(10, "K클로버"),

    A_DIA(1, "A다이아몬드"),
    TWO_DIA(2, "2다이아몬드"),
    THREE_DIA(3, "3다이아몬드"),
    FOUR_DIA(4, "4다이아몬드"),
    FIVE_DIA(5, "5다이아몬드"),
    SIX_DIA(6, "6다이아몬드"),
    SEVEN_DIA(7, "7다이아몬드"),
    EIGHT_DIA(8, "8다이아몬드"),
    NINE_DIA(9, "9다이아몬드"),
    TEN_DIA(10, "10다이아몬드"),
    J_DIA(10, "J다이아몬드"),
    Q_DIA(10, "Q다이아몬드"),
    K_DIA(10, "K다이아몬드"),
    ;

    private int number;
    private String format;

    Card(int number, String format) {
        this.number = number;
        this.format = format;
    }

    public int getNumber() {
        return number;
    }

    public String getFormat() {
        return format;
    }
}
