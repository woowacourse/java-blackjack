package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;

public class InitialState extends State {

    private static final int PICK_COUNT = 2;

    public InitialState(HandCard handCard) {
        super(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            handCard.add(picked);
        }
        if (handCard.isBigScoreEqual(BLACKJACK_NUMBER)) {
            return new BlackjackState(handCard);
        }
        return new DrawState(handCard);
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
