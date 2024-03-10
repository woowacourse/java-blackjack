package domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    private final List<Card> deck;
    private final RandomDrawStrategy strategy = new RandomDrawStrategy();

    public Deck() {
        this.deck = createDeck();
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
}
