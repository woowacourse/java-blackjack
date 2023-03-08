package domain.player;

import domain.game.BattingMoney;
import domain.game.Revenue;

import java.util.function.Function;

public enum GamblerCompeteResult {

    BLACK_JACK_WIN(Revenue::blackJackWin),
    WIN(Revenue::defaultWin),
    LOSE(Revenue::lose),
    DRAW(Revenue::draw),
    ;

    private final Function<BattingMoney, Revenue> revenueFunction;

    GamblerCompeteResult(final Function<BattingMoney, Revenue> revenueFunction) {
        this.revenueFunction = revenueFunction;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public Revenue revenue(final Gambler gambler) {
        return revenueFunction.apply(gambler.battingMoney());
    }
}
