package domain.card;

import static domain.card.CardName.ACE;
import static domain.card.CardName.EIGHT;
import static domain.card.CardName.FIVE;
import static domain.card.CardName.FOUR;
import static domain.card.CardName.JACK;
import static domain.card.CardName.KING;
import static domain.card.CardName.NINE;
import static domain.card.CardName.QUEEN;
import static domain.card.CardName.SEVEN;
import static domain.card.CardName.SIX;
import static domain.card.CardName.TEN;
import static domain.card.CardName.THREE;
import static domain.card.CardName.TWO;
import static domain.card.CardType.CLOVER;
import static domain.card.CardType.DIAMOND;
import static domain.card.CardType.HEART;
import static domain.card.CardType.SPADE;

public enum Card {

    ACE_HEART(ACE, HEART),
    TWO_HEART(TWO, HEART),
    THREE_HEART(THREE, HEART),
    FOUR_HEART(FOUR, HEART),
    FIVE_HEART(FIVE, HEART),
    SIX_HEART(SIX, HEART),
    SEVEN_HEART(SEVEN, HEART),
    EIGHT_HEART(EIGHT, HEART),
    NINE_HEART(NINE, HEART),
    TEN_HEART(TEN, HEART),
    JACK_HEART(JACK, HEART),
    QUEEN_HEART(QUEEN, HEART),
    KING_HEART(KING, HEART),
    ACE_SPADE(ACE, SPADE),
    TWO_SPADE(TWO, SPADE),
    THREE_SPADE(THREE, SPADE),
    FOUR_SPADE(FOUR, SPADE),
    FIVE_SPADE(FIVE, SPADE),
    SIX_SPADE(SIX, SPADE),
    SEVEN_SPADE(SEVEN, SPADE),
    EIGHT_SPADE(EIGHT, SPADE),
    NINE_SPADE(NINE, SPADE),
    TEN_SPADE(TEN, SPADE),
    JACK_SPADE(JACK, SPADE),
    QUEEN_SPADE(QUEEN, SPADE),
    KING_SPADE(KING, SPADE),
    ACE_CLOVER(ACE, CLOVER),
    TWO_CLOVER(TWO, CLOVER),
    THREE_CLOVER(THREE, CLOVER),
    FOUR_CLOVER(FOUR, CLOVER),
    FIVE_CLOVER(FIVE, CLOVER),
    SIX_CLOVER(SIX, CLOVER),
    SEVEN_CLOVER(SEVEN, CLOVER),
    EIGHT_CLOVER(EIGHT, CLOVER),
    NINE_CLOVER(NINE, CLOVER),
    TEN_CLOVER(TEN, CLOVER),
    JACK_CLOVER(JACK, CLOVER),
    QUEEN_CLOVER(QUEEN, CLOVER),
    KING_CLOVER(KING, CLOVER),
    ACE_DIAMOND(ACE, DIAMOND),
    TWO_DIAMOND(TWO, DIAMOND),
    THREE_DIAMOND(THREE, DIAMOND),
    FOUR_DIAMOND(FOUR, DIAMOND),
    FIVE_DIAMOND(FIVE, DIAMOND),
    SIX_DIAMOND(SIX, DIAMOND),
    SEVEN_DIAMOND(SEVEN, DIAMOND),
    EIGHT_DIAMOND(EIGHT, DIAMOND),
    NINE_DIAMOND(NINE, DIAMOND),
    TEN_DIAMOND(TEN, DIAMOND),
    JACK_DIAMOND(JACK, DIAMOND),
    QUEEN_DIAMOND(QUEEN, DIAMOND),
    KING_DIAMOND(KING, DIAMOND);

    private final CardName cardName;
    private final CardType cardType;

    Card(CardName cardName, CardType cardType) {
        this.cardName = cardName;
        this.cardType = cardType;
    }

    public CardName cardName() {
        return cardName;
    }

    public CardType cardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return "Card[" +
                "name=" + cardName + ", " +
                "cardType=" + cardType + ']';
    }
}
