package domain.card;

import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    public static final int INIT_CARDS_SIZE = 2;

    private Stack<Card> cards;

    public Deck(Stack<Card> cards) {
        validateDuplicate(cards);
        this.cards = cards;
    }

    private void validateDuplicate(Stack<Card> cards) {
        if (cards.size() != new HashSet<>(cards).size()) {
            throw new DeckDuplicationException();
        }
    }

    public Card dealCard() {
        fulfillIfEmpty();
        return cards.pop();
    }

    private void fulfillIfEmpty() {
        if (isEmpty()) {
            this.cards = DeckFactory.create().cards;
        }
    }

    public PlayingCards dealInitCards() {
        return IntStream.range(0, INIT_CARDS_SIZE)
                .mapToObj(i -> cards.pop())
                .collect(Collectors.collectingAndThen(Collectors.toList(), PlayingCards::new));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
