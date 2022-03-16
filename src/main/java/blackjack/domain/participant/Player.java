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
        return score < GOAL_SCORE;
    }

    public boolean isWin(int comparisonScore) {
        if (score > GOAL_SCORE) {
            return false;
        }
        if (comparisonScore > GOAL_SCORE) {
            return true;
        }
        return score >= comparisonScore;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
