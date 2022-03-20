package blackjack.domain.game;

import blackjack.domain.Name;
import blackjack.domain.state.Ready;

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
    public long getRevenue(PlayRecord playRecord, long bettingMoney) {
        return getState().revenue(playRecord, bettingMoney);
    }

    @Override
    public boolean isDrawable() {
        return getState().isDrawable();
    }

    public static void main(String[] args) {
        Ready ready = new Ready();
        long revenue = ready.revenue(PlayRecord.BLACKJACK, 10000);
        System.out.println(revenue);
    }
}
