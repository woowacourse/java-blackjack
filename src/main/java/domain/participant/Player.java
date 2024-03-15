package domain.participant;

import controller.dto.response.ParticipantHandStatus;
import domain.constants.CardCommand;
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

    @Override
    public boolean canPickCard(final DecisionToContinue decision) {
        return isNotBusted() && CardCommand.HIT.equals(decision.get());
    }

    @Override
    public ParticipantHandStatus createInitialHandStatus() {
        return createHandStatus();
    }
}
