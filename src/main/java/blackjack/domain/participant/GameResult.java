package blackjack.domain.participant;

public enum GameResult {

    WIN("승"),
    BLACKJACK("블랙잭"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GameResult calculate(final Player player, final Dealer dealer) {
        if (isWin(player, dealer)) {
            return GameResult.WIN;
        }
        if (isBlackjack(player, dealer)) {
            return GameResult.BLACKJACK;
        }
        if (isDraw(player, dealer)) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }

    private static boolean isWin(final Player player, final Dealer dealer) {
        if (player.isBlackjack()) {
            return false;
        }
        if (dealer.isBurst() && !player.isBurst()) {
            return true;
        }
        if (!dealer.isBurst() && !player.isBurst() &&
            player.getScore() > dealer.getScore()) {
            return true;
        }
        return false;
    }

    private static boolean isBlackjack(final Player player, final Dealer dealer) {
        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return true;
        }
        return false;
    }

    private static boolean isDraw(final Player player, final Dealer dealer) {
        if (!player.isBurst() && !dealer.isBurst()) {
            return dealer.getScore() == player.getScore();
        }
        return false;
    }
}
