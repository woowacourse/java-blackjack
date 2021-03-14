package blackjack.domain.player;

import blackjack.domain.Status;

public class Gamer extends Player {

    public Gamer(String name, int bettingMoney) {
        super(name, bettingMoney);
    }

    @Override
    public boolean isDrawable() {
        return getStatus() == Status.HIT;
    }

}
