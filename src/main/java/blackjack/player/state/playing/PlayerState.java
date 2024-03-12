package blackjack.player.state.playing;

import blackjack.card.Card;
import blackjack.player.Hand;
import blackjack.player.state.GameState;
import blackjack.player.state.terminated.BlackJackState;
import blackjack.player.state.terminated.BustedState;

public class PlayerState extends PlayingState {

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
}
