package domain;

public class Betting {
    public static final double BLACKJACK_BONUS = 1.5;

    private final int bettingMoney;
    private int bettingScore;

    public Betting(int bettingMoney) {
        this.bettingMoney = bettingMoney;
        this.bettingScore = 0;
    }

    public void resetMoneyZero() {
        bettingScore = 0;
    }

    public void gainMoney() {
        bettingScore = (int) ((int) bettingMoney * BLACKJACK_BONUS);
    }

    public void loseMoney() {
        bettingScore = -bettingMoney;
    }

    public int getBettingScore() {
        return bettingScore;
    }

}
