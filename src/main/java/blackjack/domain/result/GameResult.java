package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.money.Betting;
import blackjack.domain.money.Profit;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    BLACKJACK_WIN((dealer, player) -> player.isBlackjack() && !dealer.isBlackjack(), 1.5),
    WIN(((dealer, player) -> dealer.isBust() && player.isStand()), 1),
    LOSE((dealer, player) -> player.isBust() || (player.isStand() && dealer.isBlackjack()), -1),
    DRAW((dealer, player) -> player.isBlackjack() && dealer.isBlackjack(), 0);

    GameResult(BiPredicate<Dealer, Player> predicate, double multiplier) {
        this.predicate = predicate;
        this.multiplier = multiplier;
    }

    private final BiPredicate<Dealer, Player> predicate;
    private final double multiplier;

    public static GameResult createPlayerResult(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.matches(dealer, player))
                .findAny()
                .orElse(GameResult.findByScore(dealer, player));
    }

    private boolean matches(Dealer dealer, Player player) {
        return predicate.test(dealer, player);
    }

    private static GameResult findByScore(Dealer dealer, Player player) {
        if (dealer.getScore() < player.getScore()) {
            return WIN;
        }

        if (dealer.getScore() > player.getScore()) {
            return LOSE;
        }

        return DRAW;
    }

    public Profit calculateProfit(Betting bettingAmount) {
        return bettingAmount.multiply(multiplier);
    }
}
