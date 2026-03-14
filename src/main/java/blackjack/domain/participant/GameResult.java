package blackjack.domain.participant;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    WIN(GameResult::isWin, 2.0D),
    BLACKJACK(GameResult::isBlackjack, 1.5D),
    DRAW(GameResult::isDraw, 1.0D),
    LOSE(GameResult::isLose, 0D);

    private final BiPredicate<Player, Dealer> conditionChecker;
    private final double payoutCoefficient;

    GameResult(final BiPredicate<Player, Dealer> checker, final double payoutCoefficient) {
        this.conditionChecker = checker;
        this.payoutCoefficient = payoutCoefficient;
    }

    public static GameResult calculate(final Player player, final Dealer dealer) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.conditionChecker.test(player, dealer))
                .findFirst()
                .orElse(LOSE);
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

    private static boolean isLose(final Player player, final Dealer dealer) {
        return true;
    }
}
