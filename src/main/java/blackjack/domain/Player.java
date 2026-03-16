package blackjack.domain;

public class Player extends Participant {
    private static final double BONUS_AMOUNT = 1.5;
    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }


    public Money calculateFinalProfit(Participant dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return money.keepBettingAmount();
        }
        if (isBlackjack()) {
            return money.multiplyBettingAmount(BONUS_AMOUNT);
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
