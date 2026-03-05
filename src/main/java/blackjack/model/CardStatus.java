package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class CardStatus {

    private final List<Card> cards;
    private boolean blackjack;

    public CardStatus() {
        this.cards = new ArrayList<>();
        this.blackjack = false;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void markBlackjack() {
        this.blackjack = true;
    }
}
