package blackjack.domain;

import java.util.List;

public class Dealer {

    private final Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public void receiveCard(final Card card) {
        cards.save(card);
    }

    public Card openCard() {
        return cards.getCards().get(0);
    }

    public List<Card> cards() {
        return cards.getCards();
    }

}
