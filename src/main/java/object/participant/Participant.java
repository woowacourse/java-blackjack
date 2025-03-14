package object.participant;

import java.util.Map;
import object.game.GameResult;

public interface Participant {
    boolean isAbleToDraw(final int score);
    void applyGameRecord(GameResult result);
    void bet(int amount);
    boolean isDealer();
    String getNickname();
    Map<GameResult, Integer> getGameRecord();
    int getProfit();
}
