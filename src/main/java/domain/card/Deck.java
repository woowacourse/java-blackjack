package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Deck {

    private final List<Card> deck;
    private final DrawStrategy strategy;

    public Deck(DrawStrategy strategy) {
        this.deck = new ArrayList<>();
        this.strategy = strategy;
        createDeck();
    }

    private void createDeck() {
        for (CardNumber value : CardNumber.values()) {
            createCardByNumberAndShape(value);
        }
    }

    private void createCardByNumberAndShape(CardNumber value) {
        for (Shape shape : Shape.values()) {
            deck.add(new Card(value.getValue(), shape));
        }
    }

    public List<Card> drawInitialHands() {
        return List.of(draw(), draw());
    }

    public Card draw() {
        int randomNumber = strategy.generateNumber(deck.size());
        Card card = deck.get(randomNumber);
        deck.remove(randomNumber);
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck1 = (Deck) o;
        return Objects.equals(deck, deck1.deck) && Objects.equals(strategy, deck1.strategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, strategy);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                ", strategy=" + strategy +
                '}';
    }
}
