package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class DealerDrawState extends State{
    private static final int DEALER_HIT_NUMBER = 16;

    public DealerDrawState(HandCard handCard) {
        super(handCard);
    }

    @Override
    public State draw(CardDeck cardDeck) {
        handCard.add(cardDeck.pick());

        if (handCard.score().smallScore() > BLACKJACK_NUMBER && handCard.score().bigScore() > BLACKJACK_NUMBER) {
            return new BustState(handCard);
        }
        if (!isFinished()) {
            return this;
        }
        return new StandState(handCard);
    }

    @Override
    public boolean isFinished() {
        return handCard.score().bigScore() > DEALER_HIT_NUMBER;
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
