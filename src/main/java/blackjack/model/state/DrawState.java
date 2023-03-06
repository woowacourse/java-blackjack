package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

public class DrawState extends ParticipantState {
    public DrawState(HandCard handCard) {
        super(handCard);
    }

    @Override
    public ParticipantState draw(CardDeck cardDeck) {
        handCard.add(cardDeck.pick());
        if (handCard.isSmallScoreOver(BLACKJACK_NUMBER) && handCard.isBigScoreOver(BLACKJACK_NUMBER)) {
            return new BustState(handCard);
        }
        return this;
    }

    public ParticipantState turnStandState() {
        return new StandState(handCard);
    }

    public ParticipantState turnDealerDrawState() {
        return new DealerDrawState(handCard);
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
}
