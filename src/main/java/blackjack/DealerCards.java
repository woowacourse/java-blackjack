package blackjack;

import java.util.List;

public class DealerCards {

    private final List<Card> cards;

    public DealerCards(Card... cards) {
        this.cards = List.of(cards);
    }

    public int scoreForDealer() {
        int value = 0;
        for (Card card : cards) {
            value += valueForDealer(card);
        }
        return value;
    }

    private int valueForDealer(Card card) {
        if (card.isAce()) {
            return 11;
        }
        return card.getRank();
    }
}
