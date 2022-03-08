package blackjack.domain;

import java.util.List;

public class Dealer {

    private final Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public void receiveCard(Card card) {
        cards.save(card);
    }

    public List<Card> cards() {
        return cards.getCards();
    }
}
