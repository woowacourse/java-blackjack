package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Dealer implements Participant {
    private static final int STAY_THRESHOLD = 17;

    private final String nickname;
    private final Map<BattleResult, Integer> battleResult;

    private Dealer(final String nickname) {
        this.nickname = nickname;
        this.battleResult = new HashMap<>();
    }

    public static Dealer generate() {
        return new Dealer("딜러");
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < STAY_THRESHOLD;
    }

    @Override
    public Map<BattleResult, Integer> getBattleResult() {
        return battleResult;
    }
}
