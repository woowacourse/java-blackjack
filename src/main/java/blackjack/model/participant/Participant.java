package blackjack.model.participant;

import blackjack.model.state.Ready;
import blackjack.model.state.State;

public abstract class Participant {
    protected final String name;
    protected final State state;

    protected Participant(final String name) {
        checkEmpty(name);
        this.name = name;
        this.state = new Ready();
    }

    private void checkEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
        }
    }
}
