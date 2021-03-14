package blackjack.domain.card;

import java.util.Deque;

public class Deck {

    private final Deque<Card> deck;

    public Deck() {
        deck = Card.getShuffledCards();
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException("deck에 남아있는 카드가 존재하지 않습니다.");
        }
        return deck.poll();
    }
}
