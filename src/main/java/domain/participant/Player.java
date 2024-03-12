package domain.participant;

import constants.ErrorCode;
import exception.ReservedPlayerNameException;

public class Player extends Participant {

    public Player(final Name name, final Hands hands) {
        super(name, hands);
        validate(name);
    }

    private void validate(final Name name) {
        if (Dealer.NAME.equals(name.getValue())) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
        }
    }

    @Override
    public boolean canDeal() {
        return handsSum() <= Hands.BLACK_JACK;
    }
}
