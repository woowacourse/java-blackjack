package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Player implements Participant {
    public static final int BUST_THRESHOLD = 21;

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
    public boolean areYouDealer() {
        return false;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public Map<BattleResult, Integer> getBattleResult() {
        return battleResult;
    }

    @Override
    public void updateBattleResult(final BattleResult battleResult) {
        this.battleResult.merge(battleResult, 1, Integer::sum);
    }
}
