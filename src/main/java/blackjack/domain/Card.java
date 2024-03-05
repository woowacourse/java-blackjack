package blackjack.domain;

import java.util.List;

import static blackjack.domain.CardSymbol.*;

public enum Card {

    SPADE_TWO(SPADE, List.of(2)),
    SPADE_THREE(SPADE, List.of(3)),
    SPADE_FOUR(SPADE, List.of(4)),
    SPADE_FIVE(SPADE, List.of(5)),
    SPADE_SIX(SPADE, List.of(6)),
    SPADE_SEVEN(SPADE, List.of(7)),
    SPADE_EIGHT(SPADE, List.of(8)),
    SPADE_NINE(SPADE, List.of(9)),
    SPADE_JACK(SPADE, List.of(10)),
    SPADE_QUEEN(SPADE, List.of(10)),
    SPADE_KING(SPADE, List.of(10)),
    SPADE_ACE(SPADE, List.of(1, 11)),


    DIAMOND_TWO(DIAMOND, List.of(2)),
    DIAMOND_THREE(DIAMOND, List.of(3)),
    DIAMOND_FOUR(DIAMOND, List.of(4)),
    DIAMOND_FIVE(DIAMOND, List.of(5)),
    DIAMOND_SIX(DIAMOND, List.of(6)),
    DIAMOND_SEVEN(DIAMOND, List.of(7)),
    DIAMOND_EIGHT(DIAMOND, List.of(8)),
    DIAMOND_NINE(DIAMOND, List.of(9)),
    DIAMOND_JACK(DIAMOND, List.of(10)),
    DIAMOND_QUEEN(DIAMOND, List.of(10)),
    DIAMOND_KING(DIAMOND, List.of(10)),
    DIAMOND_ACE(DIAMOND, List.of(1, 11)),


    HEART_TWO(HEART, List.of(2)),
    HEART_THREE(HEART, List.of(3)),
    HEART_FOUR(HEART, List.of(4)),
    HEART_FIVE(HEART, List.of(5)),
    HEART_SIX(HEART, List.of(6)),
    HEART_SEVEN(HEART, List.of(7)),
    HEART_EIGHT(HEART, List.of(8)),
    HEART_NINE(HEART, List.of(9)),
    HEART_JACK(HEART, List.of(10)),
    HEART_QUEEN(HEART, List.of(10)),
    HEART_KING(HEART, List.of(10)),
    HEART_ACE(HEART, List.of(1, 11)),


    CLUB_TWO(CLUB, List.of(2)),
    CLUB_THREE(CLUB, List.of(3)),
    CLUB_FOUR(CLUB, List.of(4)),
    CLUB_FIVE(CLUB, List.of(5)),
    CLUB_SIX(CLUB, List.of(6)),
    CLUB_SEVEN(CLUB, List.of(7)),
    CLUB_EIGHT(CLUB, List.of(8)),
    CLUB_NINE(CLUB, List.of(9)),
    CLUB_JACK(CLUB, List.of(10)),
    CLUB_QUEEN(CLUB, List.of(10)),
    CLUB_KING(CLUB, List.of(10)),
    CLUB_ACE(CLUB, List.of(1, 11));

    private final CardSymbol symbol;
    private final List<Integer> scores;

    Card(CardSymbol symbol, List<Integer> scores) {
        this.symbol = symbol;
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
