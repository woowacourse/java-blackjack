package blackjack.domain.result;

import blackjack.domain.card.HandPair;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.money.Betting;
import blackjack.domain.money.Profit;
import blackjack.domain.state.HandState;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PlayerResult {
    BLACKJACK_WIN(pair -> pair.isPlayerOnly(HandState.BLACKJACK), 1.5),
    WIN(pair -> pair.isDealerOnly(HandState.BUST) || pair.isPlayerHighScore(), 1),
    LOSE(pair -> pair.isPlayer(HandState.BUST) || pair.isDealerOnly(HandState.BLACKJACK) || pair.isDealerHighScore(), -1),
    DRAW(pair -> pair.isBoth(HandState.BLACKJACK) || pair.isSameScore(), 0);

    PlayerResult(Predicate<HandPair> condition, double profitRate) {
        this.condition = condition;
        this.profitRate = profitRate;
    }

    private final Predicate<HandPair> condition;
    private final double profitRate;

    public static PlayerResult of(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.matches(dealer, player))
                .findAny()
                .get();
    }

    private boolean matches(Dealer dealer, Player player) {
        return condition.test(HandPair.of(dealer, player));
    }

    public Profit calculateProfit(Betting bettingAmount) {
        return bettingAmount.multiply(profitRate);
    }
}
