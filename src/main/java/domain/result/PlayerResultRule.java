package domain.result;

import java.util.function.BiFunction;

import domain.user.Dealer;
import domain.user.Player;

public enum PlayerResultRule {

    PLAYER_BUST((player, dealer) -> player.isBust(), Ratio.LOSE),
    DEALER_BUST((player, dealer) -> dealer.isBust(), Ratio.WIN),
    PLAYER_ONLY_BLACK_JACK((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack(), Ratio.BLACKJACK),
    DEALER_ONLY_BLACK_JACK((player, dealer) -> !player.isBlackJack() && dealer.isBlackJack(), Ratio.LOSE),
    EQUALS((player, dealer) -> player.compareTo(dealer) == 0, Ratio.DRAW),
    PLAYER_GREATER((player, dealer) -> player.compareTo(dealer) > 0, Ratio.WIN),
    DEALER_GREATER((player, dealer) -> player.compareTo(dealer) < 0, Ratio.LOSE);

    private final BiFunction<Player, Dealer, Boolean> condition;
    private final Ratio ratio;

    PlayerResultRule(BiFunction<Player, Dealer, Boolean> condition, Ratio ratio) {
        this.condition = condition;
        this.ratio = ratio;
    }

    public Boolean condition(Player player, Dealer dealer) {
        return condition.apply(player, dealer);
    }

    public Ratio getRatio() {
        return ratio;
    }
}
