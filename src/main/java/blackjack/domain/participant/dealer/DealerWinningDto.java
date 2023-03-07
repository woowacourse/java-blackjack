package blackjack.domain.participant.dealer;

import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Name;
import java.util.Map;

public class DealerWinningDto {
    private final Name name;
    private final Map<WinningResult, Integer> resultToCount;

    private DealerWinningDto(Name name, Map<WinningResult, Integer> resultToCount) {
        this.name = name;
        this.resultToCount = resultToCount;
    }

    public static DealerWinningDto from(Dealer dealer) {
        return new DealerWinningDto(dealer.getName(), dealer.getDealerResult());
    }

    public Name getName() {
        return name;
    }

    public Map<WinningResult, Integer> getResultToCount() {
        return resultToCount;
    }
}
