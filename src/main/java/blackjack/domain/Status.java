package blackjack.domain;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum Status {
    LOSE(-1, (dealer, player) -> player.isBust()),
    BLACKJACK(1.5, (dealer, player) -> player.isBlackJack() && !dealer.isBlackJack()),
    DRAW(0, (dealer, player) -> player.compareTo(dealer) == 0),
    WIN(1, (dealer, player) -> (dealer.isBust() && player.isNotBust())
        || player.compareTo(dealer) > 0);

    private final double earningRate;
    private final BiPredicate<Score, Score> match;

    Status(double earningRate, BiPredicate<Score, Score> match) {
        this.earningRate = earningRate;
        this.match = match;
    }

    public static Status compare(Score dealerScore, Score playerScore) {
        return Stream.of(values())
            .filter(status -> status.isMatch(dealerScore, playerScore))
            .findFirst()
            .orElse(LOSE);
    }

    private boolean isMatch(Score dealerScore, Score playerScore) {
        return match.test(dealerScore, playerScore);
    }

    public double getEarningRate() {
        return earningRate;
    }
}
