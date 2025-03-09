package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Dealer implements Participant {
    public static final int STAY_THRESHOLD = 16;

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
        return score <= STAY_THRESHOLD;
    }

    @Override
    public boolean areYouDealer() {
        return true;
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
