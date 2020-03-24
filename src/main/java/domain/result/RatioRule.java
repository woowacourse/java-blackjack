package domain.result;

import domain.user.Dealer;
import domain.user.Player;

import java.util.function.BiFunction;

public enum RatioRule {

    PLAYER_BUST((player, dealer) -> player.isBust(), PrizeRatio.LOSE),
    DEALER_BUST((player, dealer) -> dealer.isBust(), PrizeRatio.WIN),
    PLAYER_ONLY_BLACK_JACK((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack(), PrizeRatio.BLACKJACK),
    DEALER_ONLY_BLACK_JACK((player, dealer) -> !player.isBlackJack() && dealer.isBlackJack(), PrizeRatio.LOSE),
    EQUALS((player, dealer) -> player.compareTo(dealer) == 0, PrizeRatio.DRAW),
    PLAYER_GREATER((player, dealer) -> player.compareTo(dealer) > 0, PrizeRatio.WIN),
    DEALER_GREATER((player, dealer) -> player.compareTo(dealer) < 0, PrizeRatio.LOSE);

    private final BiFunction<Player, Dealer, Boolean> condition;
    private final PrizeRatio prizeRatio;

    RatioRule(BiFunction<Player, Dealer, Boolean> condition, PrizeRatio prizeRatio) {
        this.condition = condition;
        this.prizeRatio = prizeRatio;
    }

    public Boolean condition(Player player, Dealer dealer) {
        return condition.apply(player, dealer);
    }

    public PrizeRatio getPrizeRatio() {
        return prizeRatio;
    }
}
