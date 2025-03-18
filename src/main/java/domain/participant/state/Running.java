package domain.participant.state;

import domain.participant.state.hand.Score;
import domain.TrumpCard;
import domain.participant.state.hand.Hand;
import java.util.List;

public abstract class Running extends Started {

    protected Running(Hand hand, Score limitScore) {
        super(hand, limitScore);
    }

    @Override
    public final boolean canHit() {
        return true;
    }

    @Override
    public final List<TrumpCard> retrieveCards() {
        return hand.getCards();
    }

    @Override
    public final Score calculateScore() {
        return hand.calculateScore();
    }
}
