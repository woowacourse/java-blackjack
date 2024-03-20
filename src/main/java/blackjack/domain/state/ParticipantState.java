package blackjack.domain.state;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public abstract class ParticipantState {
    private final Hand hand;

    public ParticipantState(Hand hand) {
        this.hand = hand;
    }

    public abstract ParticipantState draw(Deck deck);

    public abstract ParticipantState stand();

    public abstract boolean isFinished();

    public Score calculateHand() {
        return hand.sumScores();
    }

    public abstract double getProfitRate(Hand other);

    public Hand getHand() {
        return hand;
    }
}
