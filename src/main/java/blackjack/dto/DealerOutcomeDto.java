package blackjack.dto;

import blackjack.domain.Outcome;
import java.util.List;

public class DealerOutcomeDto {

    private final int winCount;
    private final int loseCount;
    private final int pushCount;

    public DealerOutcomeDto(final int winCount, final int loseCount, final int pushCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.pushCount = pushCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getPushCount() {
        return pushCount;
    }

    public static DealerOutcomeDto toDto(final List<Outcome> outcomes) {
        return new DealerOutcomeDto(
                Outcome.calculateOutcomeCount(outcomes, Outcome.WIN),
                Outcome.calculateOutcomeCount(outcomes, Outcome.LOSE),
                Outcome.calculateOutcomeCount(outcomes, Outcome.PUSH)
        );
    }
}
