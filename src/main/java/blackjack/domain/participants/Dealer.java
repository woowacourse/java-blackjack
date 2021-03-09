package blackjack.domain.participants;

import blackjack.domain.names.Name;

public class Dealer extends Participant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    protected ParticipantState updateStatus(ParticipantState currentStatus) {
        if (isBust()) {
            return ParticipantState.BUST;
        }
        if (isOverLimit()) {
            return ParticipantState.STAY;
        }
        return currentStatus;
    }

    private boolean isOverLimit() {
        return getScore() > DEALER_LIMIT;
    }
}
