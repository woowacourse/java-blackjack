package blackjack.domain.player;

public class Player extends Participant {
    private final Money bettingAmount;

    public Player(final String name, final int bettingAmount) {
        super(name);
        this.bettingAmount = new Money(bettingAmount);
    }

    @Override
    public boolean isDrawable() {
        return !isBust();
    }

    public Money getBettingAmount() {
        return bettingAmount;
    }
}
