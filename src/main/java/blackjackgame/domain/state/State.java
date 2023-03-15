package blackjackgame.domain.state;

import java.util.List;

import blackjackgame.domain.Score;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Hand;

public abstract class State {
    private final Hand hand;

    protected State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Card card);

    public List<Card> cards() {
        return hand.cards();
    }

    public abstract State stay();

    protected final Hand hand() {
        return hand;
    }

    public final Score score() {
        return hand.score();
    }
}
