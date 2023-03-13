package domain.player.participant;

import domain.card.Card;
import domain.player.Name;
import domain.player.Player;
import domain.player.participant.betresult.resultstate.BetResultState;

import java.util.List;

public class Participant extends Player {

    private final Money money;

    public Participant(final Name name, final Money money) {
        super(name);
        this.money = money;
    }

    @Override
    public boolean canHit() {
        return hand.canMoreCard();
    }

    public Money determineBetMoney(BetResultState betResultState) {
        return betResultState.calculateBetOutcomeOf(money);
    }

    @Override
    public List<Card> faceUpFirstDeal() {
        return List.of(hand.firstCard(), hand.secondCard());
    }
}
