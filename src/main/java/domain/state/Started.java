package domain.state;

import domain.card.Card;
import domain.state.fininsh.Blackjack;
import domain.state.run.Hit;
import java.util.ArrayList;
import java.util.List;

public abstract class Started implements State {
    private final Hands hands;

    protected Started(final Hands hands) {
        this.hands = hands;
    }

    public static State ofTwoCard(final Card first, final Card second) {
        final Hands hands = new Hands(new ArrayList<>(List.of(first, second)));
        if (hands.calculateScore() == 21) {
            return new Blackjack(hands);
        }
        return new Hit(hands);
    }

    @Override
    public boolean canHit(final int upperBound) {
        return getHands().calculateScore() < upperBound;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public final int getScore() {
        return getHands().calculateScore();
    }

    @Override
    public boolean isBust() {
        return false;
    }

    public Hands getHands() {
        return hands;
    }
}
