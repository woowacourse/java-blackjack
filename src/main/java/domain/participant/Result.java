package domain.participant;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double profitRate;

    Result(double profitRate) {
        this.profitRate = profitRate;
    }

    public static Result judgeResult(Player player, Dealer dealer) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK;
        }
        if (player.isBurst() || (player.calculateScore() < dealer.calculateScore()
            && !dealer.isBurst())) {
            return LOSE;
        }
        if (dealer.isBurst() || player.calculateScore() > dealer.calculateScore()) {
            return WIN;
        }
        return DRAW;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
