package domain.fixture;

import domain.card.BlackJackScore;
import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardValue;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;

public class CardAreaFixture {

    public static CardArea over21CardArea() {
        final CardArea cardArea = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, TEN));
        cardArea.addCard(new Card(DIAMOND, TEN));
        return cardArea;
    }

    public static CardArea under21CardArea() {
        return new CardArea(new Card(DIAMOND, CardValue.TWO), new Card(DIAMOND, CardValue.TWO));
    }

    public static CardArea under16CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, CardValue.TWO));
    }

    public static CardArea over16CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, CardValue.SEVEN));
    }

    public static CardArea equal16CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, SIX));
    }

    public static CardArea equal17CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, SEVEN));
    }

    public static CardArea equal18CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, EIGHT));
    }

    public static CardArea equal19CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, NINE));
    }

    public static CardArea equal20CardArea() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, TEN));
    }

    public static CardArea equal21CardAreaNonBlackJack() {
        final CardArea cardArea = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, NINE));
        cardArea.addCard(new Card(DIAMOND, TWO));
        return cardArea;
    }

    public static CardArea equal21CardAreaBlackJack() {
        return new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, ACE));
    }

    public static CardArea equal22CardArea() {
        final CardArea cardArea = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, NINE));
        cardArea.addCard(new Card(DIAMOND, THREE));
        return cardArea;
    }

    public static CardArea scoreOf(final int score) {
        return new FixedCardArea(score);
    }

    static class FixedCardArea extends CardArea {

        private final int value;

        public FixedCardArea(final int value) {
            super(new Card(DIAMOND, TEN), new Card(DIAMOND, TEN));
            this.value = value;
        }

        @Override
        public BlackJackScore calculate() {
            return BlackJackScore.of(value);
        }
    }
}
