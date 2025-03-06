package domain.participant;

import java.util.Map;

public interface Participant {
    boolean ableToDraw(final int score);
    boolean areYouPlayer();
    Map<BattleResult, Integer> getBattleResult();

}
