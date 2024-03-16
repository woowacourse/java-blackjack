package domain.state.run;

import domain.card.Hands;
import domain.state.Started;

public abstract class Running extends Started {
    public Running(final Hands hands) {
        super(hands);
    }

    @Override
    public final boolean isRunning() {
        return true;
    }
}
