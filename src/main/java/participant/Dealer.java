package participant;

import card.Card;
import java.util.List;
import state.finished.Blackjack;
import state.started.Started;

public class Dealer extends Participant {

    public static final int DEALER_MAX_NUMBER_FOR_BUST = 16;

    public Dealer() {
        super();
    }

    public void prepareGame(final Card card1, final Card card2) {
        state = Started.start(card1, card2);
    }

    public Card firstRoundCard() {
        return state.cards().get(1);
    }

    @Override
    public boolean canReceiveCard() {
        return score() <= DEALER_MAX_NUMBER_FOR_BUST && !state.isFinished();
    }

    public List<Card> cards() {
        return state.cards().getCards();
    }

    public boolean isBlackjack() {
        return cards.calculateScore() == 21 && cards.size() == 2;
    }

    public boolean isBust() {
        return cards.calculateScore() > 21;
    }

    public boolean canReceiveCard() {
        return score() <= 16;
    }
}
