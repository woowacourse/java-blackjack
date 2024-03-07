package blackjack.domain.card;

import java.util.List;

import static blackjack.domain.card.CardScores.*;
import static blackjack.domain.card.CardSymbol.*;

public enum Card {

    SPADE_TWO(SPADE, TWO),
    SPADE_THREE(SPADE, THREE),
    SPADE_FOUR(SPADE, FOUR),
    SPADE_FIVE(SPADE, FIVE),
    SPADE_SIX(SPADE, SIX),
    SPADE_SEVEN(SPADE, SEVEN),
    SPADE_EIGHT(SPADE, EIGHT),
    SPADE_NINE(SPADE, NINE),
    SPADE_JACK(SPADE, J),
    SPADE_QUEEN(SPADE, Q),
    SPADE_KING(SPADE, K),
    SPADE_ACE(SPADE, A),

    DIAMOND_TWO(DIAMOND, TWO),
    DIAMOND_THREE(DIAMOND, THREE),
    DIAMOND_FOUR(DIAMOND, FOUR),
    DIAMOND_FIVE(DIAMOND, FIVE),
    DIAMOND_SIX(DIAMOND, SIX),
    DIAMOND_SEVEN(DIAMOND, SEVEN),
    DIAMOND_EIGHT(DIAMOND, EIGHT),
    DIAMOND_NINE(DIAMOND, NINE),
    DIAMOND_JACK(DIAMOND, J),
    DIAMOND_QUEEN(DIAMOND, Q),
    DIAMOND_KING(DIAMOND, K),
    DIAMOND_ACE(DIAMOND, A),

    HEART_TWO(HEART, TWO),
    HEART_THREE(HEART, THREE),
    HEART_FOUR(HEART, FOUR),
    HEART_FIVE(HEART, FIVE),
    HEART_SIX(HEART, SIX),
    HEART_SEVEN(HEART, SEVEN),
    HEART_EIGHT(HEART, EIGHT),
    HEART_NINE(HEART, NINE),
    HEART_JACK(HEART, J),
    HEART_QUEEN(HEART, Q),
    HEART_KING(HEART, K),
    HEART_ACE(HEART, A),

    CLUB_TWO(CLUB, TWO),
    CLUB_THREE(CLUB, THREE),
    CLUB_FOUR(CLUB, FOUR),
    CLUB_FIVE(CLUB, FIVE),
    CLUB_SIX(CLUB, SIX),
    CLUB_SEVEN(CLUB, SEVEN),
    CLUB_EIGHT(CLUB, EIGHT),
    CLUB_NINE(CLUB, NINE),
    CLUB_JACK(CLUB, J),
    CLUB_QUEEN(CLUB, Q),
    CLUB_KING(CLUB, K),
    CLUB_ACE(CLUB, A);

    private final CardSymbol symbol;
    private final CardScores scores;

    Card(CardSymbol symbol, CardScores scores) {
        this.symbol = symbol;
        this.scores = scores;
    }

    @Override
    public String toString() {
        if (COURTS.contains(scores)) {
            return scores.name() + symbol.get();
        }
        return scores.get().get(0) + symbol.get();
    }

    public List<Integer> getScores() {
        return scores.get();
    }
}
