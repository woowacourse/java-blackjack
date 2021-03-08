package blackjack.domain.participants;

import blackjack.domain.cards.Deck;

public class Dealer extends Participant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Deck deck) {
        super(DEALER_NAME, deck);
    }

    @Override
    protected ParticipantStatus updateStatus(ParticipantStatus currentStatus) {
        if (isBust()) {
            return ParticipantStatus.BUST;
        }
        if (isOverLimit()) {
            return ParticipantStatus.STAY;
        }
        return currentStatus;
    }

    private boolean isOverLimit() {
        return getScore() > DEALER_LIMIT;
    }
}
