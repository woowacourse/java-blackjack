package blackjack.domain.player;

public class BetMoney {

    private static final int WIN_RATE = 2;
    private static final double BLACKJACK_EXTRA_POINT = 1.5;

    private double money;

    public BetMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void plusMoney() {
        this.money = money * WIN_RATE;
    }

    public void minusMoney() {
        this.money = -(money * WIN_RATE);
    }

    public void plusBlackjackMoney() {
        this.money = (this.money + this.money * BLACKJACK_EXTRA_POINT);
    }

    public void minusBlackjackMoney() {
        this.money = -(this.money + this.money * BLACKJACK_EXTRA_POINT);
    }
}
