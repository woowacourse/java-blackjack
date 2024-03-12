package blackjack.participant.state;

import blackjack.card.Card;
import blackjack.participant.Hand;
import java.util.List;

public abstract class GameState {

    protected Hand hand;

    protected GameState(Hand hand) {
        this.hand = hand;
    }

    public abstract GameState drawCard(Card card);

    public abstract GameState stand();

    public abstract boolean isTerminated();

    public List<Card> cards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.getScore();
    }
}
