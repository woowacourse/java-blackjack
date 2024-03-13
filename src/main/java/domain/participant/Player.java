package domain.participant;

import static domain.participant.Dealer.DEALER_NAME;

import constants.ErrorCode;
import exception.ReservedPlayerNameException;

public class Player extends Participant {

    public Player(final Name name, final Hands hands) {
        super(name, hands);
        validate(name);
    }

    private void validate(final Name name) {
        if (DEALER_NAME.equals(name)) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
        }
    }

    @Override
    public boolean canDeal() {
        return handsSum() < Hands.BLACK_JACK;
    }
}
