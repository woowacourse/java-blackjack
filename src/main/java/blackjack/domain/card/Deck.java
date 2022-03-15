package blackjack.domain.card;

import static java.util.Arrays.*;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private static final List<Card> CARDS_CACHE = createAllCards();

    private final Deque<Card> cards;

    public Deck() {
        cards = copyAndShuffleCards();
    }

    private Deque<Card> copyAndShuffleCards() {
        Collections.shuffle(CARDS_CACHE);
        return new ArrayDeque<>(List.copyOf(CARDS_CACHE));
    }

    private static List<Card> createAllCards() {
        return stream(Suit.values())
                .flatMap(Deck::createCardStreamPerSuit)
                .collect(Collectors.toList());
    }

    private static Stream<Card> createCardStreamPerSuit(Suit suit) {
        return stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination));
    }

    public Card drawCard() {
        validateExist();
        return cards.pop();
    }

    private void validateExist() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 남은 카드가 없습니다.");
        }
    }
}
