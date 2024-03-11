package blackjack.domain.card;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.EIGHT;
import static blackjack.domain.card.CardScore.FIVE;
import static blackjack.domain.card.CardScore.FOUR;
import static blackjack.domain.card.CardScore.JACK;
import static blackjack.domain.card.CardScore.KING;
import static blackjack.domain.card.CardScore.NINE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardScore.SEVEN;
import static blackjack.domain.card.CardScore.SIX;
import static blackjack.domain.card.CardScore.TEN;
import static blackjack.domain.card.CardScore.THREE;
import static blackjack.domain.card.CardScore.TWO;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static blackjack.domain.card.CardSymbol.HEART;
import static blackjack.domain.card.CardSymbol.SPADE;

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
    DIAMOND_TEN(DIAMOND, TEN),
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
    HEART_TEN(HEART, TEN),
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
    CLUB_TEN(CLUB, TEN),
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
