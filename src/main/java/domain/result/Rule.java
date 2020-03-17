package domain.result;

import domain.user.Dealer;
import domain.user.Player;

import java.util.function.BiFunction;

public enum Rule {
    PLAYER_BUST(((player, dealer) -> player.isBust()), WinningResult.LOSE),
    DEALER_BUST(((player, dealer) -> dealer.isBust()), WinningResult.WIN),
    PLAYER_HIGHER(((player, dealer) -> player.calculatePoint() > dealer.calculatePoint()), WinningResult.WIN),
    DEALER_HIGHER(((player, dealer) -> player.calculatePoint() < dealer.calculatePoint()), WinningResult.LOSE),
    PLAYER_BLACK_JACK((player, dealer) -> player.isBlackJack() && !dealer.isBlackJack(), WinningResult.WIN),
    DEALER_BLACK_JACK((player, dealer) -> !player.isBlackJack() && dealer.isBlackJack(), WinningResult.LOSE),
    EQUALS(((player, dealer) -> player.calculatePoint() == dealer.calculatePoint()), WinningResult.DRAW);

    private BiFunction<Player, Dealer, Boolean> compare;
    private WinningResult winningResult;

    Rule(BiFunction<Player, Dealer, Boolean> compare, WinningResult winningResult) {
        this.compare = compare;
        this.winningResult = winningResult;
    }

    public Boolean compare(Player player, Dealer dealer) {
        return compare.apply(player, dealer);
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }
}
