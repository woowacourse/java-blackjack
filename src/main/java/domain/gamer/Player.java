package domain.gamer;

import exception.NotAllowedNameException;

public class Player extends Gamer {
    public static final String DEALER_NAME = "딜러";
    private static final int STAY_CONDITION = 21;

    public Player(final Name name) {
        super(name);
        validateInvalidName(name);
    }

    private void validateInvalidName(final Name name) {
        if (name.isSame(DEALER_NAME)) {
            throw new NotAllowedNameException(NotAllowedNameException.NOT_ALLOWED_NAME);
        }
    }

    @Override
    int getStayCondition() {
        return STAY_CONDITION;
    }
}
