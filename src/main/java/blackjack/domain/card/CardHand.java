package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
    private final List<Card> cards;

    public CardHand() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }
}
