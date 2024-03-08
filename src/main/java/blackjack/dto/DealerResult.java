package blackjack.dto;

import blackjack.domain.ResultStatus;
import java.util.Objects;

public class DealerResult {

    private final int wins;
    private final int loses;
    private final int draws;

    public DealerResult(final int wins, final int loses, final int draws) {
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public static DealerResult of(final ResultStatus resultStatus) {
        return new DealerResult(resultStatus.getWins(), resultStatus.getLoses(), resultStatus.getDraws());
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getDraws() {
        return draws;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DealerResult that = (DealerResult) o;
        return getWins() == that.getWins() && getLoses() == that.getLoses() && getDraws() == that.getDraws();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWins(), getLoses(), getDraws());
    }
}
