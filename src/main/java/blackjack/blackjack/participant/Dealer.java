package blackjack.blackjack.participant;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.result.ProfitResult;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import blackjack.blackjack.state.running.DealerRunning;
import java.util.ArrayList;
import java.util.List;

public final class Dealer implements Participant, GameRule {

    private static final String NICKNAME = "딜러";

    private State state;

    public Dealer(final Hand hand) {
        this.state = DealerRunning.initialState(hand);
    }

    public Dealer() {
        this(new Hand(new ArrayList<>()));
    }

    @Override
    public Hand showInitialCards() {
        return new Hand(List.of(state.cards().getFirstCard()));
    }

    @Override
    public boolean canHit() {
        return state.isNotFinished();
    }

    @Override
    public int calculateScore() {
        return state.calculateScore();
    }

    @Override
    public void changeState(final State inputState) {
        this.state = inputState;
    }

    @Override
    public Hand showAllCards() {
        return state.cards();
    }

    public void dealInitialCards(final Deck deck, final int count) {
        Hand dealerHand = deck.drawCardsByCount(count);
        changeState(state.receiveCards(dealerHand));
    }

    public void receiveCards(final Hand hand) {
        changeState(state.receiveCards(hand));
    }

    public void stayIfRunning() {
        changeState(state.stay());
    }

    @Override
    public ProfitResult calculateProfit(final Players players) {
        return ProfitResult.from(this, players);
    }

    public StateType getStateType() {
        return state.getStateType();
    }

    public String getNickname() {
        return NICKNAME;
    }
}
