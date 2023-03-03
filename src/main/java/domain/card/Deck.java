package domain.card;

import domain.card.shuffler.DeckShuffler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Deck {

    private final Stack<Card> deck;

    public Deck(DeckShuffler shuffler) {
        this.deck = shuffler.shuffleDeck(initializeDeck());
    }

    private static Stack<Card> initializeDeck() {
        Stack<Card> deck = new Stack<>();
        Arrays.stream(Value.values())
                .map(Value::getValue)
                .forEach(value -> Arrays.stream(Shape.values())
                        .map(Shape::getShape)
                        .forEach(shape -> deck.push(new Card(value, shape))));
        return deck;
    }

    public List<Card> getInitialDeck() {
        return new ArrayList<>(List.of(deck.pop(), deck.pop()));
    }

    public boolean contains(final Card card) {
        return deck.contains(card);
    }

    public Card getCard() {
        return deck.pop();
    }
}
