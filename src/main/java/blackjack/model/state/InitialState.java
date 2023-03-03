package blackjack.model.state;

import blackjack.Hand;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;

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
        if (hand.score().bigScore() == BLACKJACK_NUMBER) {
            return new BlackjackState(hand);
        }
        return new DrawState(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isStand() {
        return false;
    }
}
