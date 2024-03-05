package blackjack.model;

import java.util.Arrays;
import java.util.List;

public enum Deck {
    SPADE_ACE("스페이드", 11),
    SPADE_TWO("스페이드", 2),
    SPADE_THREE("스페이드", 3),
    SPADE_FOUR("스페이드", 4),
    SPADE_FIVE("스페이드", 5),
    SPADE_SIX("스페이드", 6),
    SPADE_SEVEN("스페이드", 7),
    SPADE_EIGHT("스페이드", 8),
    SPADE_NINE("스페이드", 9),
    SPADE_TEN("스페이드", 10),
    SPADE_KING("스페이드", 10),
    SPADE_QUEEN("스페이드", 10),
    SPADE_JACK("스페이드", 10),

    DIA_ACE("다이아", 11),
    DIA_TWO("다이아", 2),
    DIA_THREE("다이아", 3),
    DIA_FOUR("다이아", 4),
    DIA_FIVE("다이아", 5),
    DIA_SIX("다이아", 6),
    DIA_SEVEN("다이아", 7),
    DIA_EIGHT("다이아", 8),
    DIA_NINE("다이아", 9),
    DIA_TEN("다이아", 10),
    DIA_KING("다이아", 10),
    DIA_QUEEN("다이아", 10),
    DIA_JACK("다이아", 10),

    CLOVER_ACE("클로버", 11),
    CLOVER_TWO("클로버", 2),
    CLOVER_THREE("클로버", 3),
    CLOVER_FOUR("클로버", 4),
    CLOVER_FIVE("클로버", 5),
    CLOVER_SIX("클로버", 6),
    CLOVER_SEVEN("클로버", 7),
    CLOVER_EIGHT("클로버", 8),
    CLOVER_NINE("클로버", 9),
    CLOVER_TEN("클로버", 10),
    CLOVER_KING("클로버", 10),
    CLOVER_QUEEN("클로버", 10),
    CLOVER_JACK("클로버", 10),

    HEART_ACE("하트", 11),
    HEART_TWO("하트", 2),
    HEART_THREE("하트", 3),
    HEART_FOUR("하트", 4),
    HEART_FIVE("하트", 5),
    HEART_SIX("하트", 6),
    HEART_SEVEN("하트", 7),
    HEART_EIGHT("하트", 8),
    HEART_NINE("하트", 9),
    HEART_TEN("하트", 10),
    HEART_KING("하트", 10),
    HEART_QUEEN("하트", 10),
    HEART_JACK("하트", 10);

    private final String type;
    private final int score;

    Deck(final String type, final int score) {
        this.type = type;
        this.score = score;
    }

    public static List<Deck> getAll() {
        return Arrays.asList(Deck.values());
    }
}
