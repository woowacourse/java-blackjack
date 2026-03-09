package domain.participant;

import domain.card.Card;

public class Participant {
    private final PlayerName name;
    private final Hand hand;

    public Participant(PlayerName name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void keepCard(Card card) {
        hand.addCard(card);
    }

    public int handSize() {
        return hand.getHandSize();
    }

    public int getTotalCardScore() {
        return hand.calculateTotalScore();
    }

    public String getName() {
        return name.getName();
    }

    public Hand getHand() {
        return hand;
    }
}
