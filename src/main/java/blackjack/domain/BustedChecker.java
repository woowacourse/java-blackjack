package blackjack.domain;

import java.util.function.BiPredicate;

public enum BustedChecker {
    PLAYER_BUSTED((player, dealer) -> player.isBusted()),
    DEALER_BUSTED((player, dealer) -> dealer.isBusted()),
    ALL_BUSTED((player, dealer) -> player.isBusted() && dealer.isBusted()),
    NONE((player, dealer) -> true);

    private final BiPredicate<Player, Dealer> predicate;

    BustedChecker(BiPredicate<Player, Dealer> predicate) {
        this.predicate = predicate;
    }

    public boolean check(Player player, Dealer dealer) {
        return predicate.test(player, dealer);
    }
}
