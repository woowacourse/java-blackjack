package blackjack.domain;

public class Player extends Participant {
    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public Money calculateFinalProfit(Participant dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return money.keepBettingAmount();
        }
        if (isBlackjack()) {
            return money.multiplyBettingAmount(1.5);
        }
        if (isPlayerLoss(dealer)) {
            return money.lossBettingAmount();
        }
        return money.keepBettingAmount();
    }

    private boolean isPlayerLoss(Participant dealer) {
        if (isBust()) {
            return true;
        }
        if (dealer.isBlackjack()) {
            return true;
        }
        return isScoreLowerThanDealer(dealer);
    }

    private boolean isScoreLowerThanDealer(Participant dealer) {
        return calculateTotalScore() < dealer.calculateTotalScore() && !dealer.isBust();
    }
}
