package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck = createDeck();
    }

    private List<Card> createDeck() {
        return IntStream.range(1, 13)
                .boxed()
                .flatMap(cardNumber -> IntStream.range(0, 3)
                        .mapToObj(index -> new Card(cardNumber, Shape.getShapeByIndex(index))))
                .collect(Collectors.toList());
    }

    public Card draw(DrawStrategy strategy) {
        int randomNumber = strategy.generateNumber(deck.size());
        Card card = deck.get(randomNumber);
        deck.remove(randomNumber);
        return card;
    }
}
