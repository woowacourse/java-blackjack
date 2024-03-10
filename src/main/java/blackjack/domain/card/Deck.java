package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final ArrayDeque<Card> deck;

    public Deck(final ArrayDeque<Card> cards) {
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
        return EnumSet.allOf(CardValue.class)
                      .stream()
                      .map(cardNumber -> new Card(cardNumber, cardSymbol));
    }

    public Card draw() {
        return deck.pollLast();
    }
}
