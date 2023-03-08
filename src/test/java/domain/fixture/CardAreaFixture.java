package domain.fixture;

import domain.card.Card;
import domain.card.CardArea;

import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;

public class CardAreaFixture {

    public static CardArea over21CardArea() {
        final CardArea cardArea = CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, TEN));
        cardArea.addCard(new Card(DIAMOND, TEN));
        return cardArea;
    }

    public static CardArea under21CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TWO, TWO));
    }

    public static CardArea under16CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, TWO));
    }

    public static CardArea over16CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, SEVEN));
    }

    public static CardArea equal16CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, SIX));
    }

    public static CardArea equal17CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, SEVEN));
    }

    public static CardArea equal18CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, EIGHT));
    }

    public static CardArea equal19CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, NINE));
    }

    public static CardArea equal20CardArea() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, TEN));
    }

    public static CardArea equal21CardAreaNonBlackJack() {
        final CardArea cardArea = CardArea.withTwoCard(CardDeckFixture.cardDeck(NINE, TEN));
        cardArea.addCard(new Card(DIAMOND, TWO));
        return cardArea;
    }

    public static CardArea equal21CardAreaBlackJack() {
        return CardArea.withTwoCard(CardDeckFixture.cardDeck(TEN, ACE));
    }

    public static CardArea equal22CardArea() {
        final CardArea cardArea = CardArea.withTwoCard(CardDeckFixture.cardDeck(NINE, TEN));
        cardArea.addCard(new Card(DIAMOND, THREE));
        return cardArea;
    }
}
