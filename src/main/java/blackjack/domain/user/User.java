package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class User {
    private final Hand hand;
    private final String name;

    public User(String name) {
        this.hand = Hand.createEmptyHand();
        this.name = name;
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
