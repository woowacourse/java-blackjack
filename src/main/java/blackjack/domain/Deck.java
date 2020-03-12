package blackjack.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private final LinkedList<Card> deck;

    public Deck(LinkedList<Card> cards) {
        this.deck = Objects.requireNonNull(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new NullPointerException("덱이 비었습니다");
        }
        return deck.poll();
    }

    public UserCards drawInitialCards() {
        return new UserCards(IntStream.of(0, 2).mapToObj(t -> this.drawCard())
                .collect(Collectors.toList()));
    }
}
