package blackjack.domain.participant;

public class Player extends Participant {

    private int bettingMoney;

    public Player(String name) {
        super(name);
    }

    public void betMoney(int money) {
        this.bettingMoney = money;
    }

    public boolean isHittable() {
        return score < TARGET_SCORE_FOR_BLACKJACK;
    }

    public boolean isWin(int comparisonScore) {
        if (score > TARGET_SCORE_FOR_BLACKJACK) {
            return false;
        }
        if (comparisonScore > TARGET_SCORE_FOR_BLACKJACK) {
            return true;
        }
        return score >= comparisonScore;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
