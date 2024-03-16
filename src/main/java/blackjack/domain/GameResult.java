package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    BLACKJACK(1.5, GameResult::isBlackJackCondition),
    WIN(1, GameResult::isWinCondition),
    LOSE(-1, GameResult::isLoseCondition),
    PUSH(0, GameResult::isPushCondition);
    
    private final double profitRate;
    private final BiPredicate<Dealer, Player> condition;

    GameResult(double profitRate, BiPredicate<Dealer, Player> condition) {
        this.profitRate = profitRate;
        this.condition = condition;
    }

    private static boolean isBlackJackCondition(final Dealer dealer, final Player player) {
        return !dealer.isBlackJack() && player.isBlackJack();
    }

    private static boolean isWinCondition(final Dealer dealer, final Player player) {
        return (dealer.isBust()) ||
                (!player.isBust() && player.calculateScore() > dealer.calculateScore());
    }

    private static boolean isLoseCondition(final Dealer dealer, final Player player) {
        return player.isBust() ||
                (dealer.isBlackJack() && !player.isBlackJack()) ||
                (player.calculateScore() < dealer.calculateScore());
    }

    private static boolean isPushCondition(final Dealer dealer, final Player player) {
        return (dealer.isBust() && player.isBust()) ||
                (dealer.isBlackJack() && player.isBlackJack()) ||
                (!player.isBust() && player.calculateScore() == dealer.calculateScore());
    }

    public static GameResult findGameResult(final Dealer dealer, final Player player) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.condition.test(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("플레이어 [" + player.getName() + "]에 해당하는 게임 결과가 없습니다."));
    }

    public static long calculatePlayerProfit(final Dealer dealer, final Player player) {
        GameResult gameResult = findGameResult(dealer, player);
        return (long) (player.getBetAmount().value() * gameResult.getProfitRate());
    }

    public static long calculateDealerProfit(final Dealer dealer, final Player player) {
        return calculatePlayerProfit(dealer, player) * -1;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
