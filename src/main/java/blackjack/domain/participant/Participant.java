package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.state.State;
import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected State state;

    protected Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    public abstract boolean shouldReceive();

    public void hit(Card card) {
        state = state.draw(card);
    }

    public boolean isReady() {
        return !state.isRunning();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }

    public String getName() {
        return name.getName();
    }

    public State getState() {
        return state;
    }

    public Card getOpenCard() {
        Hand cardHand = state.hand();
        return cardHand.openCard();
    }

    public int getCardTotalScore() {
        Hand cardHand = state.hand();
        return cardHand.getScore();
    }

    public List<Card> getCards() {
        Hand cardHand = state.hand();
        return cardHand.getCards();
    }
}
