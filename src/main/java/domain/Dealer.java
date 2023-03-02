package domain;

import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;
import java.util.List;

public class Dealer {

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.toList());
    }
}
