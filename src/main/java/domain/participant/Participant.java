package domain.participant;

import java.util.Map;

public interface Participant {
    boolean ableToDraw(final int score);
    boolean areYouDealer();
    String getNickname();
    void addGameRecord(BattleResult result);
    Map<BattleResult, Integer> getGameRecord();
}
