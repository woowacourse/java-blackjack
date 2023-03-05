package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (CardShape cardShape : CardShape.values()) {
            getMakeCardsOf(cardShape);
        }
        Collections.shuffle(cards);
    }

    private void getMakeCardsOf(final CardShape cardShape) {
        for (int i = CardNumber.getMinValue(); i <= CardNumber.getMaxValue(); i++) {
            cards.add(Card.create(cardShape, CardNumber.of(i)));
        }
    }

    public Card drawCard() {
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException("카드 업슝", exception);
        }
    }

}
