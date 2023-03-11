package blackjack.domain.user;

import blackjack.constants.ErrorCode;
import blackjack.domain.user.exception.ReservedPlayerNameException;

public class Player extends Participant {

    public Player(String name) {
        super(new Name(name));
        validateReservedName(name);
    }

    private void validateReservedName(String name) {
        if (DEALER_NAME.equals(name)) {
            throw new ReservedPlayerNameException(ErrorCode.RESERVED_NAME);
        }
    }

    @Override
    public boolean isDrawable() {
        return this.getState().isNotBust();
    }
}
