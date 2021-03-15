package blackjack.dto;

import blackjack.domain.Outcome;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Player;

public class PlayerResult {
    private final String name;
    private final BettingMoney inputMoney;
    private final BettingMoney returnMoney;
    private final Outcome outcome;

    public PlayerResult(Player player, Outcome outcome) {
        this.name = player.getName();
        this.outcome = outcome;
        this.inputMoney = player.getBettingMoney();
        this.returnMoney = player.getBettingMoney()
            .multiply(outcome.getEarningRate());
    }

    public String getName() {
        return name;
    }

    public BettingMoney getInputMoney() {
        return inputMoney;
    }

    public BettingMoney getReturnMoney() {
        return returnMoney;
    }

    public Outcome getOutcome() {
        return outcome;
    }

}
