package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    BLACKJACK_WIN(1.5, (dealer, player) -> dealer.hasNoBlackJackHand() && player.hasBlackJackHand()),
    PLAYER_LOSE(-1.0, (dealer, player) -> player.isBusted() || dealer.hasScoreAbove(player)),
    PLAYER_WIN(1.0, (dealer, player) -> (player.isNotBusted() && (dealer.isBusted() || player.hasScoreAbove(dealer)))),
    PUSH(0.0, (dealer, player) -> player.isNotBusted() && dealer.isNotBusted() && player.hasSameScore(dealer));


    private final double profitLeverage;
    private final BiPredicate<Dealer, Player> biPredicate;

    GameResult(double profitLeverage, BiPredicate<Dealer, Player> biPredicate) {
        this.profitLeverage = profitLeverage;
        this.biPredicate = biPredicate;
    }

    public static GameResult of(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.biPredicate.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[INTERNAL ERROR] 게임 결과를 판정할 수 없습니다"));
    }

    public double getProfitLeverage() {
        return profitLeverage;
    }
}
