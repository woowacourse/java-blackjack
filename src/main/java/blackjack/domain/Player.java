package blackjack.domain;

public class Player extends Participant {

    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public int calculateFinalProfit(Participant dealer) {
        if (this.isBlackjack() && dealer.isBlackjack()) {
            return money.getBettingMoney();
        }

        if (this.isBlackjack() && !dealer.isBlackjack()) {
            return (int) (money.getBettingMoney() * 1.5);
        }

        if (this.isBust() || dealer.isBlackjack()) {
            return -money.getBettingMoney();
        }

        if (this.calculateTotalScore() > dealer.calculateTotalScore()) {
            return money.getBettingMoney();
        }
        return 0;
    }
}
