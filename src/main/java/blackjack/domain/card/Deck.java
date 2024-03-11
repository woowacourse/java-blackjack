package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final Deque<Card> deck;

    public Deck(final Deque<Card> cards) {
        this.deck = cards;
    }

    public static Deck createPack() {
        final List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }

    private static List<Card> createCards() {
        return Arrays.stream(CardSymbol.values())
                     .flatMap(Deck::addCard)
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Stream<Card> addCard(final CardSymbol cardSymbol) {
        return Arrays.stream(CardValue.values())
                     .map(cardNumber -> new Card(cardNumber, cardSymbol));
    }

    public Card draw() {
        return deck.pollLast();
    }
}
