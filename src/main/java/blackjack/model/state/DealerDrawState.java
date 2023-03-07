package blackjack.model.state;

import blackjack.model.card.HandCard;
import blackjack.model.card.CardDeck;

import static blackjack.model.participant.Participant.BLACKJACK_NUMBER;

public class DealerDrawState implements ParticipantState {
    private static final int DEALER_HIT_NUMBER = 16;

    public DealerDrawState() {
    }

    @Override
    public ParticipantState draw(CardDeck cardDeck, HandCard handCard) {
        handCard.add(cardDeck.pick());

        if (handCard.isBigScoreOver(BLACKJACK_NUMBER) && handCard.isSmallScoreOver(BLACKJACK_NUMBER)) {
            return new BustState();
        }
        if (!handCard.isBigScoreOver(DEALER_HIT_NUMBER)) {
            return this;
        }
        return new StandState();
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
