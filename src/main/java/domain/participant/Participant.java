package domain.participant;

import java.util.Map;

public interface Participant {
    boolean ableToDraw(final int score);
    boolean areYouDealer();
    String getNickname();
    Map<BattleResult, Integer> getBattleResult();
    void updateBattleResult(final BattleResult battleResult);
}
