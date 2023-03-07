package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.HandCard;

import static blackjack.model.participant.Dealer.DEALER_HIT_NUMBER;
import static blackjack.model.participant.Participant.BLACKJACK_NUMBER;

public class DealerInitialState implements ParticipantState {
    private static final int PICK_COUNT = 2;

    public DealerInitialState() {
    }

    @Override
    public ParticipantState draw(CardDeck cardDeck, HandCard handCard) {
        for (int i = 0; i < PICK_COUNT; i++) {
            Card picked = cardDeck.pick();
            handCard.add(picked);
        }
        if (handCard.isBigScoreEqual(BLACKJACK_NUMBER)) {
            return new BlackjackState();
        }
        if (handCard.isBigScoreOver(DEALER_HIT_NUMBER)) {
            return new StandState();
        }
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
