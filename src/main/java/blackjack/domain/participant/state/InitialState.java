package blackjack.domain.participant.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class InitialState extends HandState {
    public InitialState() {
        this.cards = new ArrayList<>();
    }

    public InitialState(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public HandState add(final Card card) {
        cards.add(card);
        if (cards.size() < INITIAL_CARD_GIVEN) {
            return new InitialState(cards);
        }
        if (cards.size() == INITIAL_CARD_GIVEN && calculateScore() == MAXIMUM_SCORE) {
            return new BlackjackState(cards);
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
