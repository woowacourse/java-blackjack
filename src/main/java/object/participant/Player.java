package object.participant;

import java.util.Map;
import object.game.BattleResult;

public class Player implements Participant {
    private static final int STAY_THRESHOLD = 21;

    private final String nickname;
    private final GameRecord gameRecord;

    private Player(final String nickname) {
        this.nickname = nickname;
        this.gameRecord = new GameRecord();
    }

    public static Player from(final String nickname) {
        return new Player(nickname);
    }

    @Override
    public boolean ableToDraw(final int score) {
        return score < STAY_THRESHOLD;
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
    public void addGameRecord(BattleResult result) {
        gameRecord.add(result);
    }

    @Override
    public Map<BattleResult, Integer> getGameRecord() {
        return gameRecord.getGameRecord();
    }
}
