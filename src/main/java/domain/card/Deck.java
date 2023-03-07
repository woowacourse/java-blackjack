package domain.card;

import domain.CardShuffler;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public final class Deck {

    private final Queue<Card> deck;

    private Deck(final Queue<Card> cards) {
        this.deck = cards;
    }

    public static Deck create(final CardShuffler cardShuffler) {
        final List<CardPattern> cardPatterns = CardPattern.getAll();
        final List<CardNumber> cardNumbers = CardNumber.getAll();
        final List<Card> cards = makeCards(cardPatterns, cardNumbers);
        final List<Card> shuffledCards = cardShuffler.shuffle(cards);

        return new Deck(new LinkedList<>(shuffledCards));
    }

    public Card draw() {
        return deck.poll();
    }

    private static List<Card> makeCards(final List<CardPattern> cardPatterns, final List<CardNumber> cardNumbers) {
        return cardPatterns.stream()
                .flatMap(pattern -> cardNumbers.stream()
                        .map(number -> Card.create(pattern, number)))
                .collect(Collectors.toList());
    }
}
