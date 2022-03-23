package domain.participant;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN(1,
        (player, dealer) -> (!player.isBlackJack() && (player.calculateScore() > dealer.calculateScore()) && !player.isBust())
            || dealer.isBust()),
    LOSE(-1, (player, dealer) -> player.isBust() || (!player.isBlackJack() && dealer.isBlackJack())
        || (player.calculateScore() < dealer.calculateScore() && !dealer.isBust())),
    PUSH(0, (player, dealer) -> player.calculateScore() == dealer.calculateScore()),
    BLACKJACK(1.5, (player, dealer) -> player.isBlackJack() && !dealer.isBlackJack());

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
