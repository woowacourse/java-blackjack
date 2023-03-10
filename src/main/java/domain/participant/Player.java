package domain.participant;

import domain.Money;

public final class Player extends Participant {
    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);
    private Money betMoney;

    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isSmallerOrEqualsTo(BUST_BOUNDARY_EXCLUSIVE);
    }

    public void betMoney(final Money money) {
        this.betMoney = money;
    }
}
