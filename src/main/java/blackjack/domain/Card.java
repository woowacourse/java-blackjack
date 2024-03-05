package blackjack.domain;

import static blackjack.domain.CardSymbol.*;

public enum Card {
    SPADE_TWO(SPADE, 2),
    SPADE_THREE(SPADE, 3),
    SPADE_FOUR(SPADE, 4),
    SPADE_FIVE(SPADE, 5),
    SPADE_SIX(SPADE, 6),
    SPADE_SEVEN(SPADE, 7),
    SPADE_EIGHT(SPADE, 8),
    SPADE_NINE(SPADE, 9),
    SPADE_JACK(SPADE, 10),
    SPADE_QUEEN(SPADE, 10),
    SPADE_KING(SPADE, 10),
    SPADE_ACE(SPADE, 1),


    DIAMOND_TWO(DIAMOND, 2),
    DIAMOND_THREE(DIAMOND, 3),
    DIAMOND_FOUR(DIAMOND, 4),
    DIAMOND_FIVE(DIAMOND, 5),
    DIAMOND_SIX(DIAMOND, 6),
    DIAMOND_SEVEN(DIAMOND, 7),
    DIAMOND_EIGHT(DIAMOND, 8),
    DIAMOND_NINE(DIAMOND, 9),
    DIAMOND_JACK(DIAMOND, 10),
    DIAMOND_QUEEN(DIAMOND, 10),
    DIAMOND_KING(DIAMOND, 10),
    DIAMOND_ACE(DIAMOND, 1),


    HEART_TWO(HEART, 2),
    HEART_THREE(HEART, 3),
    HEART_FOUR(HEART, 4),
    HEART_FIVE(HEART, 5),
    HEART_SIX(HEART, 6),
    HEART_SEVEN(HEART, 7),
    HEART_EIGHT(HEART, 8),
    HEART_NINE(HEART, 9),
    HEART_JACK(HEART, 10),
    HEART_QUEEN(HEART, 10),
    HEART_KING(HEART, 10),
    HEART_ACE(HEART, 1),


    CLUB_TWO(CLUB, 2),
    CLUB_THREE(CLUB, 3),
    CLUB_FOUR(CLUB, 4),
    CLUB_FIVE(CLUB, 5),
    CLUB_SIX(CLUB, 6),
    CLUB_SEVEN(CLUB, 7),
    CLUB_EIGHT(CLUB, 8),
    CLUB_NINE(CLUB, 9),
    CLUB_JACK(CLUB, 10),
    CLUB_QUEEN(CLUB, 10),
    CLUB_KING(CLUB, 10),
    CLUB_ACE(CLUB, 1);


    private final CardSymbol symbol;
    private final int score;

    Card(CardSymbol symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }
}
