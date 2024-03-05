package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public void append(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
