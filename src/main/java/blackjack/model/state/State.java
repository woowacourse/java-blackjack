package blackjack.model.state;

import blackjack.model.participant.Hand;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;

import java.util.List;

public abstract class State {
    protected static final int BLACKJACK_NUMBER = 21;

    protected final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(CardDeck cardDeck);

    public abstract boolean isFinished();

    public abstract boolean isBlackjack();

    public abstract boolean isBust();

    public abstract boolean isStand();

    public CardScore getScore() {
        return hand.score();
    }

/*    public List<Card> getCards() {
        return hand.getCards();
    }*/

    public Hand getHand() {
        return hand;
    }
}
