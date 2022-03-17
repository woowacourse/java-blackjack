package blackjack.domain.gamer;

import blackjack.domain.money.Money;

public class Player extends Gamer {

    private static final int DRAWABLE_NUMBER = 21;

    private Money money;

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }

    public void betAmount(int amount) {
        money = new Money(amount);
    }

    public int getMoney() {
        return money.getAmount();
    }
}
