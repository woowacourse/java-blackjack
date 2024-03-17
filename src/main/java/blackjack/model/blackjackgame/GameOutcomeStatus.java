package blackjack.model.blackjackgame;

import blackjack.model.participants.Betting;
import java.util.function.Function;

public enum GameOutcomeStatus {
    BLACKJACK(betting -> new Profit(betting.getAmount() * 1.5)),
    WIN(betting -> new Profit(betting.getAmount())),
    LOSE(betting -> new Profit(-betting.getAmount())),
    PUSH(betting -> Profit.getDefaults());

    private final Function<Betting, Profit> playerProfitCalculator;

    GameOutcomeStatus(final Function<Betting, Profit> playerProfitCalculator) {
        this.playerProfitCalculator = playerProfitCalculator;
    }

    public Profit calculate(final Betting betting) {
        return playerProfitCalculator.apply(betting);
    }
}
