package domain.score;

import domain.game.Bet;

import java.util.function.Function;

public enum RevenueCalculator {

    EARN(1),
    LOSE(-1),
    RETURN(0),
    BLACKJACK_EARN(1.5);

    private final Function<Bet, Integer> calculate;

    RevenueCalculator(double ratio) {
        this.calculate = bet -> (int) (bet.getAmount() * ratio);
    }

    public Revenue calculate(Bet bet) {
        return new Revenue(calculate.apply(bet));
    }
}
