package domain.participant;

import controller.dto.response.ParticipantHandStatus;
import controller.dto.response.ParticipantProfitResponse;
import domain.constants.CardCommand;
import domain.constants.Outcome;
import domain.game.DecisionToContinue;
import domain.money.BettingMoney;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(final String name) { // TODO: 테스트를 위한 생성자이다. 괜찮은가?
        super(name);
        bettingMoney = new BettingMoney(0);
    }

    public Player(final String name, final int bettingAmount) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingAmount);
    }

    public ParticipantProfitResponse createPlayerProfitResponse(final Outcome outcome) {
        return new ParticipantProfitResponse(name, calculatePlayerProfit(outcome));
    }

    private int calculatePlayerProfit(final Outcome outcome) {
        double rates = outcome.earningRates();
        int bettingMoney = bettingMoney();
        return (int) Math.ceil(bettingMoney * rates);
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
