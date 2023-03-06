package domain.box;

import domain.user.PlayerStatus;
import java.util.Objects;

public class GameBoxInfo implements Comparable<GameBoxInfo> {

    private final PlayerStatus playerResult;
    private final int point;

    public GameBoxInfo(PlayerStatus playerResult, int point) {
        this.playerResult = playerResult;
        this.point = point;
    }

    public boolean isOnTurn() {
        return playerResult == PlayerStatus.HIT_ABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameBoxInfo that = (GameBoxInfo) o;
        return point == that.point && playerResult == that.playerResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerResult, point);
    }

    @Override
    public int compareTo(GameBoxInfo o) {
        if (this.playerResult == PlayerStatus.BUST && o.playerResult == PlayerStatus.BUST) {
            return 0;
        }
        if (this.playerResult == PlayerStatus.BUST) {
            return -1;
        }
        if (o.playerResult == PlayerStatus.BUST) {
            return 1;
        }
        return Integer.compare(this.point, o.point);
    }
}
