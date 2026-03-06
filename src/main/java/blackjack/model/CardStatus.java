package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class CardStatus {

    private final List<Card> cards;

    public CardStatus() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
