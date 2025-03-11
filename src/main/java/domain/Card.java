package domain;

import static domain.CardRank.ACE;
import static domain.CardRank.EIGHT;
import static domain.CardRank.FIVE;
import static domain.CardRank.FOUR;
import static domain.CardRank.JACK;
import static domain.CardRank.KING;
import static domain.CardRank.NINE;
import static domain.CardRank.QUEEN;
import static domain.CardRank.SEVEN;
import static domain.CardRank.SIX;
import static domain.CardRank.TEN;
import static domain.CardRank.THREE;
import static domain.CardRank.TWO;
import static domain.CardShape.CLOVER;
import static domain.CardShape.DIA;
import static domain.CardShape.HEART;
import static domain.CardShape.SPADE;

import java.util.Arrays;

public enum Card {
    SPADE_ACE(ACE, SPADE),
    SPADE_TWO(TWO, SPADE),
    SPADE_THREE(THREE, SPADE),
    SPADE_FOUR(FOUR, SPADE),
    SPADE_FIVE(FIVE, SPADE),
    SPADE_SIX(SIX, SPADE),
    SPADE_SEVEN(SEVEN, SPADE),
    SPADE_EIGHT(EIGHT, SPADE),
    SPADE_NINE(NINE, SPADE),
    SPADE_TEN(TEN, SPADE),
    SPADE_JACK(JACK, SPADE),
    SPADE_QUEEN(QUEEN, SPADE),
    SPADE_KING(KING, SPADE),

    HEART_ACE(ACE, HEART),
    HEART_TWO(TWO, HEART),
    HEART_THREE(THREE, HEART),
    HEART_FOUR(FOUR, HEART),
    HEART_FIVE(FIVE, HEART),
    HEART_SIX(SIX, HEART),
    HEART_SEVEN(SEVEN, HEART),
    HEART_EIGHT(EIGHT, HEART),
    HEART_NINE(NINE, HEART),
    HEART_TEN(TEN, HEART),
    HEART_JACK(JACK, HEART),
    HEART_QUEEN(QUEEN, HEART),
    HEART_KING(KING, HEART),

    DIA_ACE(ACE, DIA),
    DIA_TWO(TWO, DIA),
    DIA_THREE(THREE, DIA),
    DIA_FOUR(FOUR, DIA),
    DIA_FIVE(FIVE, DIA),
    DIA_SIX(SIX, DIA),
    DIA_SEVEN(SEVEN, DIA),
    DIA_EIGHT(EIGHT, DIA),
    DIA_NINE(NINE, DIA),
    DIA_TEN(TEN, DIA),
    DIA_JACK(JACK, DIA),
    DIA_QUEEN(QUEEN, DIA),
    DIA_KING(KING, DIA),

    CLOVER_ACE(ACE, CLOVER),
    CLOVER_TWO(TWO, CLOVER),
    CLOVER_THREE(THREE, CLOVER),
    CLOVER_FOUR(FOUR, CLOVER),
    CLOVER_FIVE(FIVE, CLOVER),
    CLOVER_SIX(SIX, CLOVER),
    CLOVER_SEVEN(SEVEN, CLOVER),
    CLOVER_EIGHT(EIGHT, CLOVER),
    CLOVER_NINE(NINE, CLOVER),
    CLOVER_TEN(TEN, CLOVER),
    CLOVER_JACK(JACK, CLOVER),
    CLOVER_QUEEN(QUEEN, CLOVER),
    CLOVER_KING(KING, CLOVER);

    private final CardRank cardRank;
    private final CardShape cardShape;

    Card(CardRank cardRank, CardShape cardShape) {
        this.cardRank = cardRank;
        this.cardShape = cardShape;
    }

    public static Cards initializeCards() {
        return new Cards(Arrays.stream(Card.values()).toList());
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public String getCardName() {
        return cardRank.getName() + cardShape.getName();
    }

    public boolean isAce() {
        return cardRank == ACE;
    }
}
