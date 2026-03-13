package blackjack.domain;

public class Player extends Participant {

    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public int calculateFinalProfit(Participant dealer) {
        int profitAmount = 0;
        if (this.isBlackjack() && !dealer.isBlackjack()) {
            profitAmount = (int) (money.getBettingMoney() * 1.5);
        }
        return profitAmount;
    }
}
