package blackjack.domain.game;

import java.util.List;

import blackjack.domain.Name;

public final class Dealer extends Participant {

    private static final int HIT_CONDITION = 16;
    private static final String NAME = "딜러";

    Dealer() {
        super(Name.of(NAME));
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable() && getCards().sum() <= HIT_CONDITION;
    }

    public long getRevenue(List<Player> players) {
        return -players.stream()
            .mapToLong(player -> player.getRevenue(this))
            .sum();
    }

}
