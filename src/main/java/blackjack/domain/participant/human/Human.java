package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Finished;
import blackjack.domain.state.running.Ready;
import java.util.List;

public abstract class Human {
    protected final Name name;
    protected State state;


    protected Human(List<Card> cards, Name name) {
        this.name = name;
        this.state = getInitState(cards);
    }

    private State getInitState(List<Card> cards) {
        State state = new Ready();
        for (Card card : cards) {
            state = state.draw(card);
        }
        return state;
    }

    public void addCard(final Card card) {
        state = state.draw(card);
    }

    public State getState() {
        return state;
    }

    public void setStay() {
        state = state.stay();
    }

    public int getPoint() {
        return getCards().getPoint();
    }

    public String getName() {
        return name.get();
    }

    public List<Card> getRawCards() {
        return getCards().getCopy();
    }

    protected Cards getCards() {
        return state.cards();
    }
}
