package blackjack.participant.state.playing;

import blackjack.card.Card;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;
import blackjack.participant.state.terminated.BlackJackState;
import blackjack.participant.state.terminated.BustedState;
import blackjack.participant.state.terminated.StandState;

public class PlayerState extends GameState {

    public PlayerState(Hand hand) {
        super(hand);
    }

    public PlayerState() {
        super(new Hand());
    }

    @Override
    public GameState drawCard(Card card) {
        Hand newHand = hand.addCard(card);
        if (newHand.isBlackJack()) {
            return new BlackJackState(newHand);
        }
        if (newHand.isBusted()) {
            return new BustedState(newHand);
        }
        return new PlayerState(newHand);
    }

    @Override
    public GameState stand() {
        return new StandState(hand);
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
