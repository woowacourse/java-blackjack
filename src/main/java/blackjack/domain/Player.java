package blackjack.domain;

public class Player extends Participant {

    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public long calculateFinalProfit(Participant dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return money.getBettingMoney();
        }
        if (isBlackjack()) {
            return (long) (money.getBettingMoney() * 1.5);
        }
        if (isBust() || dealer.isBlackjack() || (calculateTotalScore() < dealer.calculateTotalScore() && !dealer.isBust())) {
            return -money.getBettingMoney();
        }
        return money.getBettingMoney();
    }
}
