package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.participant.Hand;

public class InitialState extends State {

    private static final int PICK_COUNT = 2;

    public InitialState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            hand.add(picked);
        }
        if (hand.getScore() == BLACKJACK_NUMBER) {
            return new BlackjackState(hand);
        }
        return new DrawState(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
