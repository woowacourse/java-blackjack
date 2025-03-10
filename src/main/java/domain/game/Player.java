package domain.game;

public class Player extends Participant {

    private final String name;
    private int bettingAmount;

    public Player(String name, int bettingAmount) {
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public boolean isUnderBurstBound() {
        return !this.isOverBurstBound();
    }

    public int calculateBettingAmount(GameResult gameResult) {
        if (gameResult == GameResult.BLACKJACK_WIN) {
            this.bettingAmount = this.bettingAmount + this.bettingAmount / 2;
        }
        if (gameResult == GameResult.LOSE) {
            this.bettingAmount = -this.bettingAmount;
        }
        if (gameResult == GameResult.DRAW) {
            this.bettingAmount = 0;
        }
        return this.bettingAmount;
    }

    public String getName() {
        return name;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
