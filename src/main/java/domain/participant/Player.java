package domain.participant;

public class Player extends Participant {
    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean canDraw() {
        return !getScore().isBust() && !getScore().isBlackjack();
    }
}
