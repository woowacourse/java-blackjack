package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private static final int DECK_TOP_POSITION = 0;
    private final List<Card> deck;

    public CardDeck() {
        deck = readySixDeck();
        Collections.shuffle(deck);
    }

    private List<Card> readySixDeck() {
        List<Card> sixDeck = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            sixDeck.addAll(readyCards());
        }
        return sixDeck;
    }

    private List<Card> readyCards() {
        return Stream.of(CardPattern.values())
                .flatMap(this::createCardsForPattern)
                .collect(Collectors.toList());
    }

    private Stream<Card> createCardsForPattern(CardPattern pattern) {
        return Stream.of(CardNumber.values())
                .map(number -> new Card(number, pattern));
    }

    public Card pickCard() {
        return deck.remove(DECK_TOP_POSITION);
    }
}
