package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public boolean contains(Card card) {
        return deck.contains(card);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public long countOfAce() {
        return deck.stream()
            .filter(Card::isAce)
            .count();
    }

    public int totalScore() {
        return deck.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(deck);
    }
}
