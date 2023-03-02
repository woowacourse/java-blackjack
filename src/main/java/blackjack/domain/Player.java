package blackjack.domain;

import static blackjack.domain.BlackJackConstant.BLACKJACK;

public class Player extends Participant {

    private final Name name;

    protected Player(final String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore < BLACKJACK;
    }

    public String getName() {
        return name.getName();
    }
}
