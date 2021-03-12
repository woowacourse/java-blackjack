package blackjack.domain.gametable;

import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;

public class PlayerResult {
    private final Name name;
    private final BettingMoney returnMoney;
    private final Outcome outcome;

    public PlayerResult(Player player, Outcome outcome) {
        this.name = player.getName();
        this.outcome = outcome;
        this.returnMoney = player.getBettingMoney()
            .multiply(outcome.getEarningRate());
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getReturnMoney() {
        return returnMoney;
    }

    public Outcome getOutcome() {
        return outcome;
    }

}
