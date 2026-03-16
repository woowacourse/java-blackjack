package domain.member;

import domain.card.Card;
import domain.state.Bust;
import domain.state.State;

import java.util.List;

public class MemberInfo {
    public static final String DEALER_NAME = "딜러";

    private final String name;
    private State state;

    public MemberInfo(State state) {
        this.name = DEALER_NAME;
        this.state = state;
    }

    public MemberInfo(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public String name() {
        return name;
    }

    public State state() {
        return state;
    }

    public int currentScore() {
        return state.hand().calculateTotalValue();
    }

    public List<Card> currentCards() {
        return state.hand().getAllCard();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void receiveCard(Card card) {
        state = state.draw(card);
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public void changeToStay() {
        this.state = state.stay();
    }
}
