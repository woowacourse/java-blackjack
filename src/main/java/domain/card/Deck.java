package domain.card;

import java.util.List;
import java.util.stream.Collectors;

public final class Deck {

    private final List<Card> deck;
    private final CardSelector cardSelector;

    private Deck(final List<Card> deck, final CardSelector cardSelector) {
        this.deck = deck;
        this.cardSelector = cardSelector;
    }

    public static Deck create(final CardSelector cardSelector) {
        final List<Shape> shapes = Shape.findAllCardPattern();
        final List<Denomination> denominations = Denomination.findTotalCardNumber();
        final List<Card> deck = makeCards(shapes, denominations);

        return new Deck(deck, cardSelector);
    }

    private static List<Card> makeCards(final List<Shape> shapes, final List<Denomination> denominations) {
        return shapes.stream()
                .flatMap(pattern -> denominations.stream()
                        .map(number -> Card.of(pattern, number)))
                .collect(Collectors.toList());
    }

    public Card draw() {
        int cardOrder = cardSelector.selectCardOrder(deck.size());

        return deck.get(cardOrder);
    }
}
