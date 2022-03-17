package blackjack.domain;

import blackjack.domain.User.Dealer;
import blackjack.domain.User.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PlayerResult {
    BLACKJACK(1.5, (dealer, player) -> player.isBlackJack() && !dealer.isBlackJack()),
    WIN(1, (dealer, player) -> dealer.isBust() || (player.isGreaterScoreThan(dealer) && !player.isBust())),
    DRAW(0, (dealer, player) -> isAllBlackJack(dealer, player) || dealer.isSameScoreWithNotBlackJack(player)),
    LOSE(-1, (dealer, player) -> player.isBust() || (dealer.isGreaterScoreThan(player) && !dealer.isBust())),
    ;

    private final double profit;
    private final BiPredicate<Dealer, Player> biPredicate;

    PlayerResult(double profit, BiPredicate<Dealer, Player> biPredicate) {
        this.profit = profit;
        this.biPredicate = biPredicate;
    }

    public double getProfit() {
        return profit;
    }

    public static PlayerResult valueOf(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(playerResult -> playerResult.biPredicate.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 결과를 찾을 수 없습니다."));
    }

    private static boolean isAllBlackJack(Dealer dealer, Player player) {
        return dealer.isBlackJack() && player.isBlackJack();
    }

}
