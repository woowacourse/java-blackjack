package domain.participants;

import domain.bet.Betting;
import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.state.State;
import domain.state.running.Hit;

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

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void stay() {
        state = state.stay();
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    public void drawCard(Card card) {
        this.state = getState().drawCard(card);
    }

    abstract protected Hit getStartState(Hand hand);

    abstract public HitStrategy getHitStrategy();
}
