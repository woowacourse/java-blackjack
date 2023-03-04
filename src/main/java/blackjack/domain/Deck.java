package blackjack.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

public class Deck {
    private final Deque<Card> deck;

    public Deck(Deque<Card> deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 남은 카드가 없습니다.");
        }
        return deck.poll();
    }

    public Collection<Card> getCards() {
        return Collections.unmodifiableCollection(deck);
    }
}
