package blackjack.domain.card;

import blackjack.domain.user.UserCards;
import blackjack.exception.EmptyDeckException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    public static final int FIRST_ELEMENT = 0;
    private static final int NUM_OF_INITIAL_CARDS = 2;
    private final List<Card> deck;

    public Deck(List<Card> cards) {
        this.deck = Objects.requireNonNull(cards, "덱이 null일 수 없습니다");
        Collections.shuffle(deck);
    }

    public Card draw() {
        if (deck.size() == 0) {
            throw new EmptyDeckException("덱이 비어있습니다");
        }
        return deck.remove(FIRST_ELEMENT);
    }

    public UserCards drawInitialCards() {
        return new UserCards(generateInitialCards());
    }

    private List<Card> generateInitialCards() {
        return IntStream.of(0, NUM_OF_INITIAL_CARDS).mapToObj(t -> this.draw())
                .collect(Collectors.toList());
    }
}
