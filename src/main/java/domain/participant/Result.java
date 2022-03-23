package domain.participant;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN(1,
        (player, dealer) -> !player.isBlackJack() && player.isHigherScore(dealer)
            || dealer.isBust()),
    LOSE(-1, (player, dealer) -> player.isBust() || dealer.isOnlyBlackJack(player)
        || dealer.isHigherScore(player)),
    PUSH(0, Participant::isSameScore),
    BLACKJACK(1.5, Participant::isOnlyBlackJack);

    private final double profitRate;
    private BiPredicate<Player, Dealer> condition;

    Result(double profitRate, BiPredicate<Player, Dealer> condition) {
        this.profitRate = profitRate;
        this.condition = condition;
    }

    public static Result judgeResult(Player player, Dealer dealer) {

        return Arrays.stream(Result.values())
            .filter(result -> result.condition.test(player, dealer))
            .findAny()
            .orElseThrow();
    }

    public double getProfitRate() {
        return profitRate;
    }
}
