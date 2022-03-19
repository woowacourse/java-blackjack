package blackjack.domain.player;

public enum Result {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0);

    private final double dividendRate;

    Result(double dividendRate) {
        this.dividendRate = dividendRate;
    }

    public static Result calculateResult(Player player, Dealer dealer) {
        if (player.isBlackjackResult(dealer)) {
            return Result.BLACKJACK;
        }

        if (player.isWinAgainst(dealer)) {
            return Result.WIN;
        }

        if (player.isLoseAgainst(dealer)) {
            return Result.LOSE;
        }

        throw new IllegalArgumentException("예상하지 못한 조건 발생");
    }

    public long calculateDividend(long bettingMoney) {
        return (long) (this.dividendRate * bettingMoney);
    }
}
