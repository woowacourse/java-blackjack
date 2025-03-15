package domain.game;

public class Chip {

    private int bettingAmount;

    public Chip(int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public Chip calculateBettingAmount(GameResult gameResult) {
        if (gameResult == GameResult.BLACKJACK_WIN) {
            this.bettingAmount = this.bettingAmount + this.bettingAmount / 2;
        }
        if (gameResult == GameResult.LOSE) {
            this.bettingAmount = -this.bettingAmount;
        }
        if (gameResult == GameResult.DRAW) {
            this.bettingAmount = 0;
        }
        return this;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
