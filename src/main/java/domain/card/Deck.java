package domain.card;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private final List<Card> deck;
    private final DrawStrategy strategy;

    public Deck(DrawStrategy strategy) {
        this.deck = createDeck();
        this.strategy = strategy;
    }

    private List<Card> createDeck() {
        return IntStream.range(1, 14)
                .boxed()
                .flatMap(cardNumber -> IntStream.range(0, 4)
                        .mapToObj(index -> new Card(cardNumber, Shape.getShapeByIndex(index))))
                .collect(Collectors.toList());
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
