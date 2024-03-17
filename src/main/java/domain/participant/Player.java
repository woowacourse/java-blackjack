package domain.participant;

import controller.dto.response.ParticipantHandStatus;
import domain.constants.CardCommand;
import domain.constants.Outcome;
import domain.game.DecisionToContinue;
import domain.money.BettingMoney;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(final String name, final int bettingAmount) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingAmount);
    }

    public int calculatePlayerProfit(final Outcome outcome) {
        return (int) Math.ceil(bettingMoney() * outcome.earningRates());
    }


    @Override
    public boolean canPickCard(final DecisionToContinue decision) {
        return isNotBusted() && CardCommand.HIT.equals(decision.get());
    }

    @Override
    public ParticipantHandStatus createInitialHandStatus() {
        return createHandStatus();
    }

    public int bettingMoney() {
        return this.bettingMoney.getValue();
    }
}
