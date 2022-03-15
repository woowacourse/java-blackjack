package blackjack.domain.machine;

import java.util.Arrays;

public enum Card {
    A_DIAMOND("A다이아몬드", 1),
    TWO_DIAMOND( "2다이아몬드", 2),
    THREE_DIAMOND( "3다이아몬드", 3),
    FOUR_DIAMOND( "4다이아몬드", 4),
    FIVE_DIAMOND( "5다이아몬드", 5),
    SIX_DIAMOND( "6다이아몬드", 6),
    SEVEN_DIAMOND( "7다이아몬드", 7),
    EIGHT_DIAMOND( "8다이아몬드", 8),
    NINE_DIAMOND( "9다이아몬드", 9),
    J_DIAMOND( "J다이아몬드", 10),
    Q_DIAMOND( "Q다이아몬드", 10),
    K_DIAMOND( "K다이아몬드", 10),

    A_HEART("A하트", 1),
    TWO_HEART("2하트", 2),
    THREE_HEART("3하트", 3),
    FOUR_HEART("4하트", 4),
    FIVE_HEART("5하트", 5),
    SIX_HEART("6하트", 6),
    SEVEN_HEART("7하트", 7),
    EIGHT_HEART("8하트", 8),
    NINE_HEART( "9하트", 9),
    J_HEART("J하트", 10),
    Q_HEART("Q하트", 10),
    K_HEART("K하트", 10),

    A_SPADE("A스페이드", 1),
    TWO_SPADE( "2스페이드", 2),
    THREE_SPADE( "3스페이드", 4),
    FOUR_SPADE( "4스페이드", 4),
    FIVE_SPADE("5스페이드", 5),
    SIX_SPADE( "6스페이드", 6),
    SEVEN_SPADE( "7스페이드", 7),
    EIGHT_SPADE("8스페이드", 8),
    NINE_SPADE("9스페이드", 9),
    J__SPADE("J스페이드", 10),
    Q_SPADE("Q스페이드", 10),
    K_SPADE("K스페이드", 10),

    A_CLOVER("A클로버", 1),
    TWO__CLOVER("2클로버", 2),
    THREE__CLOVER( "3클로버", 3),
    FOUR_CLOVER("4클로버", 4),
    FIVE_CLOVER("5클로버", 5),
    SIX_CLOVER( "6클로버", 6),
    SEVEN_CLOVER("7클로버", 7),
    EIGHT_CLOVER( "8클로버", 8),
    NINE_CLOVER("9클로버", 9),
    J_CLOVER("J클로버", 10),
    Q_CLOVER("Q클로버", 10),
    K_CLOVER("K클로버", 10);

    public static int SIZE = 48;

    private String name;
    private int number;

    Card(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static Card of(final int index) {
        return Arrays.stream(Card.values())
                .filter(card -> card.ordinal() == index)
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}