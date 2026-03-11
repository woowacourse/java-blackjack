package domain.participants;

import domain.bet.Betting;
import domain.hitStrategy.HitStrategy;
import domain.state.Result;
import domain.state.State;

public abstract class Participant {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    protected final String name;
    private final Betting betting;

    protected Participant(String name, Betting betting) {
        validateNameLength(name);
        this.name = name;
        this.betting = betting;
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException("플레이어 이름은 2~7자이어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public Integer getProfit(Result result) {
        return betting.getProfit(result);
    }

    abstract State getStartState(Hand hand);

    abstract public HitStrategy getHitStrategy();
}
