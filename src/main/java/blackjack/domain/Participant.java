package blackjack.domain;

import java.util.List;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract boolean canHit();

    public void receiveCard(Card card) {
        if (canHit()) {
            hand.addCard(card);
        }
    }

    public int getCardCount() {
        return hand.getCount();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }


    public int getTotalPoint() {
        return hand.getTotalPoint();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name;
    }
}
