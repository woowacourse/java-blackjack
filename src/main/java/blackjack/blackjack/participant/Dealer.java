package blackjack.blackjack.participant;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.result.ProfitResult;
import blackjack.blackjack.state.running.DealerRunning;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;
import java.util.ArrayList;
import java.util.List;

public final class Dealer extends Participant implements GameRule {

    private static final String NICKNAME = "딜러";
    private static final int SPREAD_CARD_SIZE = 2;

    private State state;

    public Dealer(final Hand hand) {
        this.state = DealerRunning.initialState(hand);
    }

    public Dealer() {
        this(new Hand(new ArrayList<>()));
    }

    public Hand showInitialCards() {
        return new Hand(List.of(state.cards().getFirstCard()));
    }

    public boolean canHit() {
        return state.isNotFinished();
    }

    @Override
    public void dealInitialCards(final Players players, final Deck deck) {
        Hand dealerHand = deck.drawCardsByCount(SPREAD_CARD_SIZE);
        changeState(state.receiveCards(dealerHand));

        Hand playerHand = deck.drawCardsByCount(SPREAD_CARD_SIZE * players.getSize());
        players.receiveCardsByCount(playerHand, SPREAD_CARD_SIZE);
    }

    public ProfitResult calculateProfit(final Players players) {
        return ProfitResult.from(this, players);
    }

    @Override
    public int calculateScore() {
        return state.calculateScore();
    }

    @Override
    public void changeState(final State inputState) {
        this.state = inputState;
    }

    public void receiveCards(final Hand hand) {
        changeState(state.receiveCards(hand));
    }

    public void stayIfRunning() {
        if (state.getStateType() == StateType.RUNNING) {
            changeState(state.stay());
        }
    }

    public Hand showAllCards() {
        return state.cards();
    }

    public State getState() {
        return state;
    }

    public String getNickname() {
        return NICKNAME;
    }
}
