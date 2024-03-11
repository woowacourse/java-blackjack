package domain.score;

import domain.player.Bet;

import java.util.function.Function;

public enum Status {

    WIN(Bet::win),
    TIE(Bet::stay),
    LOSE(Bet::lose),
    BLACKJACK(Bet::multiply);

    private final Function<Bet, Bet> function;

    Status(Function<Bet, Bet> function) {
        this.function = function;
    }

    public Bet calculateRevenue(Bet bet) {
        return function.apply(bet);
    }
}
