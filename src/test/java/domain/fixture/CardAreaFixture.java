package domain.fixture;

import domain.area.CardArea;
import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;

public class CardAreaFixture {

    public static CardArea over21CardArea() {
        final CardArea cardArea = new CardArea(new Card(CardShape.DIAMOND, CardValue.TEN), new Card(CardShape.DIAMOND, CardValue.TEN));
        cardArea.addCard(new Card(CardShape.DIAMOND, CardValue.TEN));
        return cardArea;
    }

    public static CardArea under21CardArea() {
        return new CardArea(new Card(CardShape.DIAMOND, CardValue.TWO), new Card(CardShape.DIAMOND, CardValue.TWO));
    }

    public static CardArea equal20CardArea() {
        return new CardArea(new Card(CardShape.DIAMOND, CardValue.TEN), new Card(CardShape.DIAMOND, CardValue.TEN));
    }

    public static CardArea equal21CardArea() {
        return new CardArea(new Card(CardShape.DIAMOND, CardValue.TEN), new Card(CardShape.DIAMOND, CardValue.ACE));
    }

    public static CardArea under16CardArea() {
        return new CardArea(new Card(CardShape.DIAMOND, CardValue.TEN), new Card(CardShape.DIAMOND, CardValue.TWO));
    }

    public static CardArea over16CardArea() {
        return new CardArea(new Card(CardShape.DIAMOND, CardValue.TEN), new Card(CardShape.DIAMOND, CardValue.SEVEN));
    }

    public static CardArea equal16CardArea() {
        return new CardArea(new Card(CardShape.DIAMOND, CardValue.TEN), new Card(CardShape.DIAMOND, CardValue.SIX));
    }
}
