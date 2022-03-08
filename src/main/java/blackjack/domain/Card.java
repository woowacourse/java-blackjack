package blackjack.domain;

public enum Card {

    SPADE_ACE("스페이드", "A"),
    SPADE_TWO("스페이드", "2"),
    SPADE_THREE("스페이드", "3"),
    SPADE_FOUR("스페이드", "4"),
    SPADE_FIVE("스페이드", "5"),
    SPADE_SIX("스페이드", "6"),
    SPADE_SEVEN("스페이드", "7"),
    SPADE_EIGHT("스페이드", "8"),
    SPADE_NINE("스페이드", "9"),
    SPADE_KING("스페이드", "King"),
    SPADE_QUEEN("스페이드", "Queen"),
    SPADE_JACK("스페이드", "Jack"),

    HEART_ACE("하트", "A"),
    HEART_TWO("하트", "2"),
    HEART_THREE("하트", "3"),
    HEART_FOUR("하트", "4"),
    HEART_FIVE("하트", "5"),
    HEART_SIX("하트", "6"),
    HEART_SEVEN("하트", "7"),
    HEART_EIGHT("하트", "8"),
    HEART_NINE("하트", "9"),
    HEART_KING("하트", "King"),
    HEART_QUEEN("하트", "Queen"),
    HEART_JACK("하트", "Jack"),

    DIAMOND_ACE("다이아몬드", "A"),
    DIAMOND_TWO("다이아몬드", "2"),
    DIAMOND_THREE("다이아몬드", "3"),
    DIAMOND_FOUR("다이아몬드", "4"),
    DIAMOND_FIVE("다이아몬드", "5"),
    DIAMOND_SIX("다이아몬드", "6"),
    DIAMOND_SEVEN("다이아몬드", "7"),
    DIAMOND_EIGHT("다이아몬드", "8"),
    DIAMOND_NINE("다이아몬드", "9"),
    DIAMOND_KING("다이아몬드", "King"),
    DIAMOND_QUEEN("다이아몬드", "Queen"),
    DIAMOND_JACK("다이아몬드", "Jack"),

    CLOVER_ACE("클로버", "A"),
    CLOVER_TWO("클로버", "2"),
    CLOVER_THREE("클로버", "3"),
    CLOVER_FOUR("클로버", "4"),
    CLOVER_FIVE("클로버", "5"),
    CLOVER_SIX("클로버", "6"),
    CLOVER_SEVEN("클로버", "7"),
    CLOVER_EIGHT("클로버", "8"),
    CLOVER_NINE("클로버", "9"),
    CLOVER_KING("클로버", "King"),
    CLOVER_QUEEN("클로버", "Queen"),
    CLOVER_JACK("클로버", "Jack"),
    ;

    private final String suit;
    private final String denomination;

    Card(final String suit, final String denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }
}
