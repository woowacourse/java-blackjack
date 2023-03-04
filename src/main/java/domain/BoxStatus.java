package domain;

import java.util.Objects;

public class BoxStatus implements Comparable<BoxStatus> {

    private final PlayResult playResult;
    private final int point;

    public BoxStatus(PlayResult playResult, int point) {
        this.playResult = playResult;
        this.point = point;
    }

    public boolean isOnTurn() {
        return playResult == PlayResult.NOT_BUST;
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
        return point == that.point && playResult == that.playResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playResult, point);
    }

    @Override
    public int compareTo(BoxStatus o) {
        if (this.playResult == PlayResult.BUST && o.playResult == PlayResult.BUST) {
            return 0;
        }
        if (this.playResult == PlayResult.BUST) {
            return -1;
        }
        if (o.playResult == PlayResult.BUST) {
            return 1;
        }
        return Integer.compare(this.point, o.point);
    }
}
