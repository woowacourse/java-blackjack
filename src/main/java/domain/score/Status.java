package domain.score;

import domain.player.Bet;

import java.util.function.Function;

public enum Status {

    WIN(Bet::win),
    TIE(Bet::stay),
    LOSE(Bet::lose),
    BLACKJACK(Bet::multiply);

    private final Function<Bet, Revenue> function;

    Status(Function<Bet, Revenue> function) {
        this.function = function;
    }

    public Revenue calculateRevenue(Bet bet) {
        return function.apply(bet);
    }
}
