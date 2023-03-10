package blackjack.domain.participant.dealer;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Name;
import java.util.Map;

public class DealerWinningDto {
    private final Map<WinningResult, Long> resultToCount;

    public DealerWinningDto(Map<WinningResult, Long> resultToCount) {
        this.resultToCount = resultToCount;
    }

    public Name getName() {
        return new Name("딜러");
    }

    public Map<WinningResult, Long> getResultToCount() {
        return resultToCount;
    }
}
