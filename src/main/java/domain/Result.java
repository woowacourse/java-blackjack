package domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    DEALER_WIN(
            (dealer, player) -> player.isBust() || !dealer.isBust() && dealer.getTotalScore() > player.getTotalScore()),
    PLAYER_WIN(
            (dealer, player) -> dealer.isBust() || !player.isBust() && dealer.getTotalScore() < player.getTotalScore()),
    PUSH((dealer, player) -> dealer.getTotalScore() == player.getTotalScore());

    private final BiPredicate<Dealer, Player> judgeResult;

    Result(final BiPredicate<Dealer, Player> judgeResult) {
        this.judgeResult = judgeResult;
    }

    public static Result of(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(value -> value.judgeResult.test(dealer, player))
                .findAny()
                .orElseThrow();
    }
}
