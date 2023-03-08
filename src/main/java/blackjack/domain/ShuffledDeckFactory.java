package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.game.Deck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckFactory implements DeckFactory {

    @Override
    public Deck generate() {
        final List<Card> cards = generateCards();
        Collections.shuffle(cards);

        return new Deck(new ArrayDeque<>(cards));
    }

    private List<Card> generateCards() {
        final List<Card> cards = new ArrayList<>();

        for (final Shape shape : Shape.values()) {
            generateSameShapeCards(cards, shape);
        }
        return cards;
    }

    private void generateSameShapeCards(final List<Card> cards, final Shape shape) {
        for (final Symbol symbol : Symbol.values()) {
            cards.add(new Card(shape, symbol));
        }
    }
}
