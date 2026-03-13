package domain.card;

import static domain.Constant.DELIMITER;

import domain.ExceptionMessage;
import domain.Rank;
import domain.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card pull() {
        validateIsEmpty();
        return deck.removeFirst();
    }

    private void validateIsEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_CARDS.getMessage());
        }
    }

    @Override
    public String toString() {
        return deck.stream()
                .map(card -> card.toString())
                .collect(Collectors.joining(DELIMITER));
    }
}
