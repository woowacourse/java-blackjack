package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class PlayerInitialState extends InitialState {

    public PlayerInitialState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            hand.add(picked);
        }
        return tryStateTransition();
    }

    private State tryStateTransition() {
        if (isScoreHigherBlackjackNumber()) {
            return new BlackjackState(hand);
        }
        return new PlayerDrawState(hand);
    }

    private boolean isScoreHigherBlackjackNumber() {
        return hand.getScore() == BLACKJACK_NUMBER;
    }

}
