package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    BLACKJACK(1.5, ((dealer, player)
            -> !dealer.isBlackJack() && player.isBlackJack())),
    WIN(1, ((dealer, player)
            -> (dealer.isBust()) ||
            (!player.isBust() && player.calculateScore() > dealer.calculateScore()))),
    LOSE(-1, ((dealer, player)
            -> player.isBust() ||
            (dealer.isBlackJack() && !player.isBlackJack()) ||
            (player.calculateScore() < dealer.calculateScore()))),
    PUSH(0, (dealer, player)
            -> (dealer.isBust() && player.isBust()) ||
            (dealer.isBlackJack() && player.isBlackJack()) ||
            (!player.isBust() && player.calculateScore() == dealer.calculateScore()));


    private final double profitRate;
    private final BiPredicate<Dealer, Player> condition;

    GameResult(double profitRate, BiPredicate<Dealer, Player> condition) {
        this.profitRate = profitRate;
        this.condition = condition;
    }

    public static GameResult findGameResult(final Dealer dealer, final Player player) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.condition.test(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당하는 게임 결과가 없습니다."));
    }

    public static double calculatePlayerProfit(final Dealer dealer, final Player player) {
        GameResult gameResult = findGameResult(dealer, player);
        return player.getBetAmount().value() * gameResult.getProfitRate();
    }

    public static double calculateDealerProfit(final Dealer dealer, final Player player) {
        return calculatePlayerProfit(dealer, player) * -1;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
