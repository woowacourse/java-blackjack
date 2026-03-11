package domain.pariticipant;

import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.Hand;

import static constant.BlackjackConstant.DEALER_DRAW_BOUND;

public class Dealer extends Participant {
    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public void drawAdditionalCard(Deck deck, CardShuffler cardShuffler) {
        this.drawCard(deck, cardShuffler);
    }

    public boolean shouldHit() {
        return this.getScore() <= DEALER_DRAW_BOUND;
    }
}
