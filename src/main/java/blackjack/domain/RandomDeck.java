package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDeck implements Deck{

    private final List<Card> cards = new ArrayList<>();

    public RandomDeck() {
        for (Shape shape : Shape.values()) {
            makeCardsOf(shape);
        }
        Collections.shuffle(cards);
    }

    private void makeCardsOf(final Shape shape) {
        for (int i = CardNumber.MIN_RANGE; i <= CardNumber.MAX_RANGE; i++) {
            cards.add(new Card(shape, CardNumber.of(i)));
        }
    }

    @Override
    public Card drawCard() {
        try {
            return cards.remove(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalStateException("카드 업슝", exception);
        }
    }

}
