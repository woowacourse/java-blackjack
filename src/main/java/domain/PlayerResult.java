package domain;

import domain.participant.Participant;
import java.util.Objects;

public class PlayerResult {
    private final Participant player;
    private final GameResult gameResult;

    public PlayerResult(Participant player, GameResult gameResult) {
        this.player = player;
        this.gameResult = gameResult;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerResult that = (PlayerResult) o;
        return Objects.equals(player, that.player) && gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, gameResult);
    }
}
