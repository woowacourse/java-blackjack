package blackjack.domain;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;

public class Dealer {
    private Cards cards;

    public Dealer() {
        cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
