package domain;

import java.util.List;

public class Dealer {
    private final String name;
    private final Hand hand;

    public Dealer(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void keepCard(Card card) {
        hand.addCard(card);
    }

    public Integer handSize() {
        return hand.getHandSize();
    }

    public Integer getTotalCardScore() {
        return hand.calculateTotalScore();
    }

    public Boolean dealerRule() {
        return getTotalCardScore() < 17;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand.getHand();
    }
}
