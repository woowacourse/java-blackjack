package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    BLACKJACK_WIN(1.5, (dealer, player) -> !dealer.isBlackjack() && player.isBlackjack()),
    WIN(1, ((dealer, player) -> !player.isBust() &&player.calculateScore() > dealer.calculateScore())),
    DRAW(0, (dealer, player) -> !player.isBust() && player.calculateScore() == dealer.calculateScore()),
    LOSE(-1, (dealer, player) -> player.isBust() || player.calculateScore() < dealer.calculateScore());

    private final double earnRate;
    private final BiPredicate<Dealer, Player> isMatch;

    GameResult(double rate, BiPredicate<Dealer, Player> isMatch) {
        this.earnRate = rate;
        this.isMatch = isMatch;
    }

    public static GameResult compareScore(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.isMatch.test(dealer, player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 결과가 없습니다."));
    }

    public double getEarnRate() {
        return earnRate;
    }
}
