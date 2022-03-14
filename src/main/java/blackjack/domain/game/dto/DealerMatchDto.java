package blackjack.domain.game.dto;

import blackjack.domain.game.Referee;
import blackjack.domain.game.ResultCount;
import blackjack.domain.game.ResultType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public class DealerMatchDto {
    private static final Referee REFEREE = new Referee();

    private final Map<ResultType, ResultCount> matchResult;
    private final String name;

    private DealerMatchDto(Map<ResultType, ResultCount> matchResult, String name) {
        this.matchResult = matchResult;
        this.name = name;
    }

    public static DealerMatchDto of(Dealer dealer, List<Player> players) {
        return new DealerMatchDto(REFEREE.generateDealerResult(dealer, players), dealer.getName());
    }

    public String getName() {
        return name;
    }

    public Map<ResultType, ResultCount> getMatchResult() {
        return matchResult;
    }

    @Override
    public String toString() {
        return "DealerMatchDto{" +
                "matchResult=" + matchResult +
                ", name='" + name + '\'' +
                '}';
    }
}
