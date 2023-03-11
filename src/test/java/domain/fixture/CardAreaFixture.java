package domain.fixture;

import domain.card.Card;
import domain.card.CardArea;
import domain.card.CardValue;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;

public class CardAreaFixture {

    public static CardArea over21CardArea() {
        final CardArea cardArea = withTwoCard(TEN, TEN);
        cardArea.addCard(new Card(DIAMOND, TEN));
        return cardArea;
    }

    public static CardArea under21CardArea() {
        return withTwoCard(TWO, TWO);
    }

    public static CardArea under16CardArea() {
        return withTwoCard(TEN, TWO);
    }

    public static CardArea over16CardArea() {
        return withTwoCard(TEN, SEVEN);
    }

    public static CardArea equal16CardArea() {
        return withTwoCard(TEN, SIX);
    }

    public static CardArea equal17CardArea() {
        return withTwoCard(TEN, SEVEN);
    }

    public static CardArea equal18CardArea() {
        return withTwoCard(TEN, EIGHT);
    }

    public static CardArea equal19CardArea() {
        return withTwoCard(TEN, NINE);
    }

    public static CardArea equal20CardArea() {
        return withTwoCard(TEN, TEN);
    }

    public static CardArea equal21CardAreaNonBlackJack() {
        final CardArea cardArea = withTwoCard(NINE, TEN);
        cardArea.addCard(new Card(DIAMOND, TWO));
        return cardArea;
    }

    public static CardArea equal21CardAreaBlackJack() {
        return withTwoCard(TEN, ACE);
    }

    public static CardArea equal22CardArea() {
        final CardArea cardArea = withTwoCard(NINE, TEN);
        cardArea.addCard(new Card(DIAMOND, THREE));
        return cardArea;
    }

    public static CardArea withTwoCard(final CardValue first, final CardValue second) {
        return CardArea.initialWithTwoCard(new Card(DIAMOND, first), new Card(DIAMOND, second));
    }
}
