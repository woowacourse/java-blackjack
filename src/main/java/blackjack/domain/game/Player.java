package blackjack.domain.game;

import blackjack.domain.Name;

public final class Player extends Participant {

    private final Name name;

    Player(Name name) {
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable();
    }
}
