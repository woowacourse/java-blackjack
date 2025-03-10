package model.deck;

import exception.IllegalBlackjackStateException;
import java.util.List;
import model.card.Card;

public class Deck {
    private final List<Card> deck;

    public Deck(final List<Card> deck) {
        this.deck = deck;
    }

    public Card getCard() {
        if (deck.isEmpty()) {
            throw new IllegalBlackjackStateException("덱에 카드가 존재하지 않습니다.");
        }
        return deck.removeLast();
    }
}
