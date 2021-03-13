package blackjack.domain.user;

public class Player extends User {
    private static final int HIT = 21;

    private Money money;

    public Player(String name, double bettingMoney) {
        super(new Name(name));
        this.money = new Money(bettingMoney);
    }

    public Money decide(Dealer dealer) {
        if (this.isBlackjack() || dealer.isBlackjack()) {
            this.money = decideByBlackjack(dealer, this.money);
            return this.money;
        }
        if (this.isBust() || dealer.isBust()) {
            this.money = decideByBust(dealer, this.money);
            return this.money;
        }
        this.money = decideByScore(dealer, this.money);
        return this.money;
    }

    private Money decideByBlackjack(Dealer dealer, Money money) {
        if (this.isBlackjack() && !dealer.isBlackjack()) {
            money = new Money(getMoney() * 1.5);
        }
        if (!this.isBlackjack() && dealer.isBlackjack()) {
            money = new Money(getMoney() * -1.0);
        }
        return money;
    }

    private Money decideByBust(Dealer dealer, Money money) {
        if (this.isBust() && !dealer.isBust()) {
            money = new Money(getMoney() * -1.0);
        }
        if (!this.isBust() && dealer.isBust()) {
            money = decideByPlayerState(money);
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
