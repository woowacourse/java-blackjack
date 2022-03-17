package blackjack.domain.player;

public enum Result {
    BLACKJACK,
    WIN,
    LOSE;

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

    private static boolean isBlackjack(Player player, Dealer dealer) {
        return !dealer.isBlackjack() && player.isBlackjack();
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return (dealer.isBlackjack() && player.isBlackjack()) ||
                (!player.isBlackjack() && !player.isBust() && (player.getScore() >= dealer.getScore()));

    }

    private static boolean isLose(Player player, Dealer dealer) {
        return player.isBust() || player.getScore() < dealer.getScore();
    }

}
