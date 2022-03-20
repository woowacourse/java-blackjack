package blackjack.domain.game;

import blackjack.domain.Name;

public final class Player extends Participant {

    private final Name name;
    private final Betting betting;

    Player(Name name, Betting betting) {
        this.name = name;
        this.betting = betting;
    }

    @Override
    public Name getName() {
        return name;
    }

    public long getRevenue(PlayRecord playRecord) {
        return getState().revenue(playRecord, betting);
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable();
    }
}
