package blackjack.domain.user;

public class Player extends User {
    private Money money;

    public Player(String name, double bettingMoney) {
        super(new Name(name));
        this.money = new Money(bettingMoney);
    }

    public Money getMoney() {
        return this.money;
    }

    @Override
    public boolean isHit() {
        return this.cards.calculateScore().isPlayerHit();
    }
}
