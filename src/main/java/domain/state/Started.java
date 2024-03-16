package domain.state;

import static domain.Blackjack.PERFECT_SCORE;

import domain.card.Card;
import domain.card.Hands;
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
        if (isBlackjack(hands)) {
            return new Blackjack(hands);
        }
        return new Hit(hands);
    }

    private static boolean isBlackjack(final Hands hands) {
        return hands.calculateScore() == PERFECT_SCORE;
    }

    @Override
    public double earningRate() {
        return 1;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public final int getScore() {
        return getHands().calculateScore();
    }

    public Hands getHands() {
        return hands;
    }
}
