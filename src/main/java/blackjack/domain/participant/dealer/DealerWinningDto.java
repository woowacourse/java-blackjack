package blackjack.domain.participant.dealer;

import blackjack.domain.game.Result;
import blackjack.domain.participant.Name;
import java.util.Map;

public class DealerWinningDto {
    private final Name name;
    private final Map<Result, Integer> winningMap;

    private DealerWinningDto(Name name, Map<Result, Integer> winningMap) {
        this.name = name;
        this.winningMap = winningMap;
    }

    public static DealerWinningDto from(Dealer dealer) {
        return new DealerWinningDto(dealer.getName(), dealer.getDealerResult());
    }

    public Name getName() {
        return name;
    }

    public Map<Result, Integer> getWinningMap() {
        return winningMap;
    }
}
