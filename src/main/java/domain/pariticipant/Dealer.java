package domain.pariticipant;

import domain.card.Deck;
import domain.card.Hand;

import static constant.BlackjackConstant.DEALER_DRAW_BOUND;

public class Dealer extends Participant {
    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public void drawAdditionalCard(Deck deck) {
        this.drawCard(deck);
    }

    public boolean shouldHit() {
        return this.getScore() <= DEALER_DRAW_BOUND;
    }
}
