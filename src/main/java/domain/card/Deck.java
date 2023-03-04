package domain.card;

import domain.CardSelector;

import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> deck;
    private final CardSelector cardSelector;

    private Deck(final List<Card> deck, final CardSelector cardSelector) {
        this.deck = deck;
        this.cardSelector = cardSelector;
    }

    public static Deck create(final CardSelector cardSelector) {
        final List<CardPattern> cardPatterns = CardPattern.getAll();
        final List<CardNumber> cardNumbers = CardNumber.getAll();
        final List<Card> deck = makeCards(cardPatterns, cardNumbers);

        return new Deck(deck, cardSelector);
    }

    private static List<Card> makeCards(final List<CardPattern> cardPatterns, final List<CardNumber> cardNumbers) {
        return cardPatterns.stream()
                .flatMap(pattern -> cardNumbers.stream()
                        .map(number -> Card.create(pattern, number)))
                .collect(Collectors.toList());
    }

    public Card draw() {
        int cardOrder = cardSelector.selectCardOrder(deck.size());

        return deck.get(cardOrder);
    }
}
