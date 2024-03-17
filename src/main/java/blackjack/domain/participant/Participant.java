package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final State state;

    public Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    public abstract Participant draw(Deck deck);

    public abstract Participant stand();

    public final Score calculateHand() {
        return state.calculateHand();
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    final State drawHand(Deck deck) {
        return state.draw(deck);
    }

    final State standHand() {
        return state.stand();
    }

    final double calculateProfitRate(Hand other) {
        return state.getProfitRate(other);
    }

    final Hand getHand() {
        return state.getHand();
    }

    public final Name getName() {
        return name;
    }

    final State getState() {
        return state;
    }

    public final boolean isFinished() {
        return state.isFinished();
    }

    public final List<Card> getCards() {
        return state.getHand().getCards();
    }
}
