package blackjack.domain.participant.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HitState extends HandState {
    public HitState(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public HandState add(final Card card) {
        cards.add(card);
        if (calculateScore() > MAXIMUM_SCORE) {
            return new BustState(cards);
        }
        return new HitState(cards);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
