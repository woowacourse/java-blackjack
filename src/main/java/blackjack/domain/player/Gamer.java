package blackjack.domain.player;

import blackjack.domain.Status;

public class Gamer extends Player {

    public Gamer(String name) {
        super(name);
    }

    @Override
    public boolean isDrawable() {
        return getStatus() == Status.HIT;
    }


}
