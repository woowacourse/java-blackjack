package domain.participant;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum PlayerResult {
    BLACKJACK(1.5, ((player, dealer) -> player.isBlackJack() && dealer.isNotBlackJack())),
    DRAW(0, (player, dealer) -> player.isBlackJack() && dealer.isBlackJack()
        || player.score() == dealer.score()),
    LOSE(-1, (player, dealer) -> player.isBurst()
        || (player.score() < dealer.score()) && !dealer.isBurst()),
    WIN(1, (player, dealer) -> dealer.isBurst() || player.score() > dealer.score());


    private final double profitRate;
    private final BiPredicate<Player, Dealer> condition;

    PlayerResult(double profitRate, BiPredicate<Player, Dealer> condition) {
        this.profitRate = profitRate;
        this.condition = condition;
    }

    public static PlayerResult of(Player player, Dealer dealer) {
        return Arrays.stream(values())
            .filter(result -> result.condition.test(player, dealer))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("결과 계산에 에러가 발생했습니다."));
    }

    public double getProfitRate() {
        return profitRate;
    }
}
