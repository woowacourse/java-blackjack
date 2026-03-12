package domain.participants;

import domain.bet.Betting;
import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.Started;
import domain.state.State;

public abstract class Participant {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    protected final String name;
    protected final Betting betting;
    protected State state;

    protected Participant(String name, Hand hand, Betting betting) {
        validateNameLength(name);
        this.name = name;
        this.betting = betting;
        this.state = getStartState(hand);
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("플레이어 이름은 2~7자이어야 합니다.");
        }
    }

    abstract public boolean canDraw();

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void stay() {
        state = state.stay();
    }

    public void drawCard(Card card) {
        this.state = getState().drawCard(card);
    }

    public State getStartState(Hand hand) {
        return Started.getStartState(hand);
    }
}
