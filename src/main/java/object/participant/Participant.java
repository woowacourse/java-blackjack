package object.participant;

import java.util.Map;
import object.game.GameResult;

public interface Participant {
    boolean ableToDraw(final int score);
    boolean areYouDealer();
    String getNickname();
    void applyGameRecord(GameResult result);
    Map<GameResult, Integer> getGameRecord();
    int getProfit();
    void bet(int amount);
}
