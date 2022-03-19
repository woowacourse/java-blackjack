package blackjack.user;

import blackjack.State;
import java.util.Objects;

public class Player extends Participant {
    private static final String BLANK_NAME_EXCEPTION = "[ERROR] 이름이 공백입니다.";

    private Player(String name) {
        super(name);
    }

    public static Player generate(String name) {
        Objects.requireNonNull(name);
        validateName(name);
        return new Player(name);
    }

    private static void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NAME_EXCEPTION);
        }
    }

    @Override
    public void setStateStayIfSatisfied(boolean stayFlag) {
        if (state.equals(State.HIT) && stayFlag) {
            super.state = State.STAY;
        }
    }

    @Override
    protected void updateStateAfterAddCard() {
        setStateBlackjackIfSatisfied();
        setStateBustIfSatisfied();
    }
}
