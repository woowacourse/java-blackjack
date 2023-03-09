package domain.player.participant;

import domain.player.Name;
import domain.player.Player;
import domain.player.dealer.Dealer;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import domain.player.participant.betresult.LoseState;
import domain.player.participant.betresult.NotYetState;
import domain.player.participant.betresult.WinState;

public class Participant extends Player {

    private BetResultState betResultState;
    private final Money money;

    public Participant(final Name name, final Money money) {
        super(name);
        this.money = money;
        betResultState = null;
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard();
    }

    public void determineBetState(final BetResultState betResultState) {
        this.betResultState = betResultState;
    }

    public Money determineBetMoney() {
        return betResultState.calculateBetOutcomeOf(money);
    }

    public Money determineBetMoney(final BetResultState betResultState) {
        return betResultState.calculateBetOutcomeOf(money);
    }

    public boolean hasNotBetState() {
        return betResultState == null;
    }

    public BetResultState firstMatchWith(final Dealer dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return new BreakEvenState();
        }

        if (isBlackjack() && !dealer.isBlackjack()) {
            return new WinState();
        }

        if (!isBlackjack() && dealer.isBlackjack()) {
            return new LoseState();
        }

        return new NotYetState();
    }

    public BetResultState finalMatchWith(final Dealer dealer) {
        if (isBust()) {
            return new LoseState();
        }
        if (dealer.isBust()) {
            return new BreakEvenState();
        }
        if (score().isLessThan(dealer.score())) {
            return new LoseState();
        }
        return new BreakEvenState();
    }
}
