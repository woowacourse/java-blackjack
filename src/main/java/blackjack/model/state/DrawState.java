package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

import static blackjack.model.participant.Participant.BLACKJACK_NUMBER;

public class DrawState implements ParticipantState {
    public DrawState() {
    }

    @Override
    public ParticipantState draw(CardDeck cardDeck, HandCard handCard) {
        handCard.add(cardDeck.pick());
        if (handCard.isSmallScoreOver(BLACKJACK_NUMBER) && handCard.isBigScoreOver(BLACKJACK_NUMBER)) {
            return new BustState();
        }
        return this;
    }

    public ParticipantState turnStandState() {
        return new StandState();
    }

    public ParticipantState turnDealerDrawState() {
        return new DealerDrawState();
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
