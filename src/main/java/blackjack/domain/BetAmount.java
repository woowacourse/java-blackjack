package blackjack.domain;

public class BetAmount {

    private long betMoney;
    private long earnedMoney;

    public BetAmount(final long betMoney) {
        this.betMoney = betMoney;
        this.earnedMoney = 0;
    }

    public void setBetMoney(final long betMoney) {
        this.betMoney = betMoney;
    }

    public void setEarnedMoney(final long earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public void loseAll() {
        this.earnedMoney = -1 * this.betMoney;
    }

    public void getDouble() {
        this.earnedMoney = this.betMoney;
    }

    public void getHalfTimes() {
        this.earnedMoney = this.betMoney / 2;
    }

    public void getOnce() {
        this.earnedMoney = 0;
    }

    public long getEarnedMoney() {
        return this.earnedMoney;
    }
}
