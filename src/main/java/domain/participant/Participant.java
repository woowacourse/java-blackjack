package domain.participant;

import java.util.Map;

public interface Participant {
    boolean ableToDraw(final int score);
    boolean areYouPlayer();
    String getNickname();
    Map<BattleResult, Integer> getBattleResult();
}
