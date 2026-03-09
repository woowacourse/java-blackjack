package domain.participant;

import domain.card.Card;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public void keepCard(Card card) {
        if (getTotalCardScore() < 21) {
            this.getHand().addCard(card);
        }
    }
}
