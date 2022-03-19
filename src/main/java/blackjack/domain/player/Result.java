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
        if (isBlackjack(player, dealer)) {
            return Result.BLACKJACK;
        }

        if (isWin(player, dealer)) {
            return Result.WIN;
        }

        if (isLose(player, dealer)) {
            return Result.LOSE;
        }

        throw new IllegalArgumentException("예상하지 못한 조건 발생");
    }

    public long calculateDividend(long bettingMoney) {
        return (long) (this.dividendRate * bettingMoney);
    }

    private static boolean isBlackjack(Player player, Dealer dealer) {
        return !dealer.isBlackjack() && player.isBlackjack();
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return (dealer.isBlackjack() && player.isBlackjack()) ||
                !(player.isBlackjack() || player.isBust()) && (player.getScore() >= dealer.getScore());

    }

    private static boolean isLose(Player player, Dealer dealer) {
        return player.isBust() || player.getScore() < dealer.getScore();
    }

}
