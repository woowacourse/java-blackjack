package blackjack_statepattern.participant;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;
import blackjack_statepattern.state.Ready;
import blackjack_statepattern.state.State;
import java.util.List;

public abstract class Participant {
    private final String name;
    protected State state;

    public Participant(String name) {
        this.name = name;
        this.state = new Ready();
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean isReady() {
        return state.isReady();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void receiveCard(Card card) {
        state = state.draw(card);
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return state.cards();
    }

    public List<Card> getCardsValue() {
        return state.cards().getCards();
    }

    public int getScore() {
        return state.score();
    }

    public State getState() {
        return state;
    }
}
