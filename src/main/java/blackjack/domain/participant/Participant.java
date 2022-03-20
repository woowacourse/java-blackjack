package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;

public class Participant {

    private final Name name;
    protected State state;

    public Participant(String name) {
        this(new Name(name));
    }

    private Participant(Name name) {
        this(name, new Ready());
    }

    private Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    public void hit(Card card) {
        if (!isFinished()) {
            state = state.draw(card);
        }
    }

    public boolean isReady() {
        return state.isRunning();
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
