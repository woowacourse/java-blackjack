package object.participant;

import java.util.Map;
import object.game.GameResult;

public interface Participant {
    boolean ableToDraw(final int score);
    void applyGameRecord(GameResult result);
    void bet(int amount);
    boolean areYouDealer();
    String getNickname();
    Map<GameResult, Integer> getGameRecord();
    int getProfit();
}
