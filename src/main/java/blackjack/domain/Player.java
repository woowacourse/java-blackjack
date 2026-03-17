package blackjack.domain;

public class Player extends Participant {
    private static final double BONUS_AMOUNT = 1.5;
    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public Money calculateFinalProfit(ScoreCompareResult result) {
        if (isBlackjack() && result.isPlayerWin()) {
            return money.multiplyBettingAmount(BONUS_AMOUNT);
        }
        if (result.isPlayerLoss()) {
            return money.lossBettingAmount();
        }
        return money.keepBettingAmount();
    }
}
