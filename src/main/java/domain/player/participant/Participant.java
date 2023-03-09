package domain.player.participant;

import domain.player.Name;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import domain.player.participant.betresult.LoseState;
import domain.player.participant.betresult.WinState;

public class Participant extends Player {

    private BetResultState betResultState;
    private final Money money;

    public Participant(final Name name, final Money money) {
        super(name);
        this.money = money;
        betResultState = BetResultState.NULL;
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard();
    }

    public Money determineBetMoney() {
        return betResultState.calculateBetOutcomeOf(money);
    }

    public boolean hasNotBetState() {
        return betResultState.equals(BetResultState.NULL);
    }

    private boolean hasBetState() {
        return !(hasNotBetState());
    }

    public void firstMatchWith(final Dealer dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            betResultState = new BreakEvenState();
        }
        if (isBlackjack() && !dealer.isBlackjack()) {
            betResultState = new WinState();
        }
        if (!isBlackjack() && dealer.isBlackjack()) {
            betResultState = new LoseState();
        }
    }

    public void finalMatchWith(final Dealer dealer) {
        if (hasBetState()) {
            return;
        }
        if (isBust()) {
            betResultState = new LoseState();
            return;
        }
        if (dealer.isBust()) {
            betResultState = new BreakEvenState();
            return;
        }
        if (score().isLessThan(dealer.score())) {
            betResultState = new LoseState();
            return;
        }
        betResultState = new BreakEvenState();
    }
}
