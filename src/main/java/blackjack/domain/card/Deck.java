package blackjack.domain.card;

import blackjack.exception.EmptyDeckException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    public static final int NUM_OF_INITIAL_CARDS = 2;
    private final Stack<Card> deck;

    public Deck(List<Card> cards) {
        deck = new Stack<>();
        deck.addAll(Objects.requireNonNull(cards, "덱이 null일 수 없습니다"));
        Collections.shuffle(deck);
    }

    public Card draw() {
        if (deck.size() == 0) {
            throw new EmptyDeckException("덱이 비어있습니다");
        }
        return deck.pop();
    }

    public List<Card> drawInitialCards() {
        return generateInitialCards();
    }

    private List<Card> generateInitialCards() {
        return IntStream.of(0, NUM_OF_INITIAL_CARDS).mapToObj(t -> this.draw())
                .collect(Collectors.toList());
    }
}
