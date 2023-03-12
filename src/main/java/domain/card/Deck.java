package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    public Deck() {
        this.deck = initializeDeck();
    }

    private static LinkedList<Card> initializeDeck() {
        LinkedList<Card> deck = new LinkedList<>();
        Arrays.stream(Value.values())
                .forEach(value -> Arrays.stream(Shape.values())
                        .forEach(shape -> deck.push(new Card(value, shape))));
        return deck;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.deck);
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
