package blackjack.domain.user;

public class Player extends User {
    private static final int HIT = 21;

    private final Money money;

    public Player(String name, double bettingMoney) {
        super(new Name(name));
        this.money = new Money(bettingMoney);
    }

    public Money decide(Dealer dealer) {
        Money money = this.money;
        if (this.isBlackjack() || dealer.isBlackjack()) {
            money = decideByBlackjack(dealer, money);
            return money;
        }
        if (this.isBust() || dealer.isBust()) {
            money = decideByBust(dealer, money);
            return money;
        }
        money = decideByScore(dealer, money);
        return money;
    }

    private Money decideByBlackjack(Dealer dealer, Money money) {
        if (this.isBlackjack() && dealer.isBlackjack()) {
            return money;
        }
        if (this.isBlackjack()) {
            money = new Money(getMoney() * 1.5);
            return money;
        }
        if (dealer.isBlackjack()) {
            money = new Money(getMoney() * -1.0);
            return money;
        }
        return money;
    }

    private Money decideByBust(Dealer dealer, Money money) {
        if (this.isBust() && !dealer.isBust()) {
            money = new Money(getMoney() * -1.0);
            return money;
        }
        if (!this.isBust() && dealer.isBust()) {
            money = decideByPlayerState(money);
            return money;
        }
        return money;
    }

    private Money decideByPlayerState(Money money) {
        if (this.isBlackjack()) {
            money = new Money(getMoney() * 1.5);
            return money;
        }
        return money;
    }

    private Money decideByScore(Dealer dealer, Money money) {
        if (isHigherDealerScore(dealer)) {
            money = new Money(getMoney() * -1.0);
            return money;
        }
        return money;
    }

    private boolean isHigherDealerScore(Dealer dealer) {
        return this.cards.getScore() < dealer.cards.getScore();
    }

    public double getMoney() {
        return this.money.getMoney();
    }

    @Override
    public boolean isHit() {
        return cards.calculateScore().getScore() <= HIT;
    }
}
