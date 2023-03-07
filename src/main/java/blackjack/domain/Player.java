package blackjack.domain;

import blackjack.domain.exception.ReservedPlayerNameException;

public class Player extends Participant {
    private static final String RESERVED_NAME = "딜러";

    public Player(String name) {
        super(new Name(name));
        validateReservedName(name);
    }

    private void validateReservedName(String name) {
        if (RESERVED_NAME.equals(name)) {
            throw new ReservedPlayerNameException(name);
        }
    }

    @Override
    public boolean isDrawable() {
        return this.getState().isNotBust();
    }
}
