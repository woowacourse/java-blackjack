package domain.participants;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.state.Started;
import domain.state.State;
import domain.state.generator.FinishedStateGenerator;
import java.util.List;

public abstract class Participant {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    protected final String name;
    protected final HitStrategy hitStrategy;
    protected State state;

    protected Participant(String name, HitStrategy hitStrategy) {
        validateNameLength(name);
        this.name = name;
        this.hitStrategy = hitStrategy;
        this.state = null;
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

    public boolean canDraw() {
        return !state.isFinished() && hitStrategy.canHit(getState().getScore());
    }

    public void stay() {
        state = state.stay();
    }

    public void drawCard(Card card) {
        if (state == null || !canDraw()) {
            throw new IllegalStateException("카드를 뽑을 수 없는 State 입니다.");
        }
        this.state = getState().drawCard(card);
    }

    public void startState(List<FinishedStateGenerator> finishedStateGenerators, Hand hand) {
        if (hand.getSize() != Hand.MIN_SIZE) {
            throw new IllegalStateException("시작 전에 핸드가 추가되어 오작동 할 여지가 있습니다.");
        }
        this.state = Started.getStartState(finishedStateGenerators, hand);
    }
}
