package blackjack.domain.betting;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    BLACKJACK(1.5, (dealer, player) -> player.isBlackjack() && !dealer.isBlackjack()),
    PLAYER_LOSE(-1.0, (dealer, player) -> player.isBusted()
            || (!dealer.isBusted() && dealer.score() > player.score())),
    PLAYER_WIN(1.0, (dealer, player) -> dealer.isBusted()
            || (!player.isBusted() && dealer.score() < player.score())),
    PUSH(0.0, (dealer, player) -> (player.isBlackjack() && dealer.isBlackjack())
            || (dealer.score() == player.score()));

    private final double profitRate;
    private final BiPredicate<Dealer, Player> condition;

    GameResult(final double profitRate, final BiPredicate<Dealer, Player> condition) {
        this.profitRate = profitRate;
        this.condition = condition;
    }

    static GameResult doesPlayerWin(final Dealer dealer, final Player player) {
        return Arrays.stream(GameResult.values())
                .filter(gameResult -> gameResult.condition.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("게임 결과를 판정할 수 없습니다."));
    }

    double getProfitRate() {
        return profitRate;
    }
}
