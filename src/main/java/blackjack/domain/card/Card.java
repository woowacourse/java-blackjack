package blackjack.domain.card;

import static blackjack.domain.card.CardScore.*;
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
    SPADE_JACK(SPADE, JACK),
    SPADE_QUEEN(SPADE, QUEEN),
    SPADE_KING(SPADE, KING),
    SPADE_ACE(SPADE, ACE),

    DIAMOND_TWO(DIAMOND, TWO),
    DIAMOND_THREE(DIAMOND, THREE),
    DIAMOND_FOUR(DIAMOND, FOUR),
    DIAMOND_FIVE(DIAMOND, FIVE),
    DIAMOND_SIX(DIAMOND, SIX),
    DIAMOND_SEVEN(DIAMOND, SEVEN),
    DIAMOND_EIGHT(DIAMOND, EIGHT),
    DIAMOND_NINE(DIAMOND, NINE),
    DIAMOND_JACK(DIAMOND, JACK),
    DIAMOND_QUEEN(DIAMOND, QUEEN),
    DIAMOND_KING(DIAMOND, KING),
    DIAMOND_ACE(DIAMOND, ACE),

    HEART_TWO(HEART, TWO),
    HEART_THREE(HEART, THREE),
    HEART_FOUR(HEART, FOUR),
    HEART_FIVE(HEART, FIVE),
    HEART_SIX(HEART, SIX),
    HEART_SEVEN(HEART, SEVEN),
    HEART_EIGHT(HEART, EIGHT),
    HEART_NINE(HEART, NINE),
    HEART_JACK(HEART, JACK),
    HEART_QUEEN(HEART, QUEEN),
    HEART_KING(HEART, KING),
    HEART_ACE(HEART, ACE),

    CLUB_TWO(CLUB, TWO),
    CLUB_THREE(CLUB, THREE),
    CLUB_FOUR(CLUB, FOUR),
    CLUB_FIVE(CLUB, FIVE),
    CLUB_SIX(CLUB, SIX),
    CLUB_SEVEN(CLUB, SEVEN),
    CLUB_EIGHT(CLUB, EIGHT),
    CLUB_NINE(CLUB, NINE),
    CLUB_JACK(CLUB, JACK),
    CLUB_QUEEN(CLUB, QUEEN),
    CLUB_KING(CLUB, KING),
    CLUB_ACE(CLUB, ACE);

    private final CardSymbol symbol;
    private final CardScore score;

    Card(CardSymbol symbol, CardScore score) {
        this.symbol = symbol;
        this.score = score;
    }

    public CardSymbol getSymbol() {
        return symbol;
    }

    public CardScore getScore() {
        return score;
    }
}
