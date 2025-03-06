package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Player implements Participant {
    private static final int BUST_THRESHOLD = 21;

    private final String nickname;
    private final Map<BattleResult, Integer> battleResult;

    private Player(final String nickname) {
        this.nickname = nickname;
        this.battleResult = new HashMap<>();
    }

    public static Player from(final String nickname) {
        return new Player(nickname);
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < BUST_THRESHOLD;
    }

    @Override
    public Map<BattleResult, Integer> getBattleResult() {
        return battleResult;
    }
}
