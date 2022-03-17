package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public enum Result {

    BLACKJACK(1.5,
        (dealer, player) -> player.isBlackjack() && !dealer.isBlackjack()),
    WIN(1,
        (dealer, player) -> dealer.isBust() ||
            (player.isBlackjack() && !dealer.isBlackjack()) ||
            (!player.isBust() && (player.calculateScore() > dealer.calculateScore()))),
    DRAW(0,
        (dealer, player) -> (!player.isBlackjack() && !dealer.isBlackjack()) &&
            (player.calculateScore() == dealer.calculateScore())),
    LOSE(-1,
        (dealer, player) -> player.isBust() ||
            (!player.isBlackjack() && dealer.isBlackjack()) ||
            (player.calculateScore() < dealer.calculateScore())),
    ;

    private final double profit;
    private final BiPredicate<Gamer, Gamer> calculateResult;

    Result(double profit, BiPredicate<Gamer, Gamer> calculateResult) {
        this.profit = profit;
        this.calculateResult = calculateResult;
    }

    public static Result judge(Dealer dealer, Player player) {
        return Arrays.stream(Result.values())
            .filter(result -> result.calculateResult.test(dealer, player))
            .findAny()
            .orElseThrow();
    }

    public int calculateRevenue(int money) {
        return (int)(profit * money);
    }

    public int calculateReverseRevenue(int money) {
        return (int)(LOSE.profit * money);
    }
}
