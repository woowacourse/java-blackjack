package blackjack.domain.user;

public class Player extends User {
    private static final int HIT = 21;

    private final Money money;

    public Player(String name, double bettingMoney) {
        super(new Name(name));
        this.money = new Money(bettingMoney);
    }

    public Money decide(Dealer dealer) {
        if (this.isBust()) {
            return new Money(getMoney() * -1);
        }
        if (dealer.isBust()) {
            return decideByPlayerState();
        }
        return decideByBlackjack(dealer);
    }

    private Money decideByPlayerState() {
        if (this.isBlackjack()) {
            return new Money(getMoney() * 1.5);
        }
        return this.money;
    }

    private Money decideByBlackjack(Dealer dealer) {
        if (this.isBlackjack() && dealer.isBlackjack()) {
            return this.money;
        }
        if (this.isBlackjack()) {
            return new Money(getMoney() * 1.5);
        }
        if (dealer.isBlackjack()) {
            return new Money(getMoney() * -1);
        }
        return this.money;
    }

    public double getMoney() {
        return this.money.getMoney();
    }

    @Override
    public boolean isHit() {
        return cards.calculateScore().getScore() <= HIT;
    }
}
