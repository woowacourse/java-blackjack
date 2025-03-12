package domain.participant;

import domain.card.CardHand;

public class Player extends GameParticipant {
    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }
}
