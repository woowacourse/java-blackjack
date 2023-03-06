package domain.box;

import java.util.Objects;

public class BoxStatus implements Comparable<BoxStatus> {

    private final PlayerStatus playerResult;
    private final int point;

    public BoxStatus(PlayerStatus playerResult, int point) {
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
        BoxStatus that = (BoxStatus) o;
        return point == that.point && playerResult == that.playerResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerResult, point);
    }

    @Override
    public int compareTo(BoxStatus o) {
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
