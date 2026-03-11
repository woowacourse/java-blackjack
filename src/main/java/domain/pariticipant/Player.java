package domain.pariticipant;

import domain.card.Deck;
import domain.card.Hand;

public class Player extends Participant {

    private final BettingAmount bettingAmount;

    public Player(Name name, Hand hand, BettingAmount bettingAmount) {
        super(name, hand);
        this.bettingAmount = bettingAmount;
    }

    public void hitCard(Deck deck) {
        this.drawCard(deck);
    }
}
