package object.participant;

import java.util.Map;
import object.game.GameResult;

public interface Participant {
    boolean ableToDraw(final int score);
    boolean areYouDealer();
    String getNickname();
    void addGameRecord(GameResult result);
    Map<GameResult, Integer> getGameRecord();
}
