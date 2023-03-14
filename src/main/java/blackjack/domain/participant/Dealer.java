package blackjack.domain.participant;

import blackjack.domain.card.Hand;

public class Dealer extends Participant {

    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int DEALER_HIT_BASED_NUMBER = 16;
    private static final int FIRST_CARD_COUNT = 2;

    public Dealer(ParticipantName participantName, Hand hand) {
        super(participantName, hand);
    }

    public int calculateDealerCardNumber() {
        if (participantHasAceCard() && getHand().getReceivedCards().size() == FIRST_CARD_COUNT) {
            return calculateCardNumber();
        }
        return calculateCardNumber();
    }

    @Override
    public boolean decideHit() {
        return calculateCardNumber() <= DEALER_HIT_BASED_NUMBER;
    }
}
