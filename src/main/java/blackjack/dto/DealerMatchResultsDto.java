package blackjack.dto;

import blackjack.model.BlackJackGame;
import blackjack.model.MatchResult;
import blackjack.model.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerMatchResultsDto {
    private final Map<String, Integer> dealerMatchResults;
    private final String name;

    public DealerMatchResultsDto(Map<MatchResult, Integer> results, Player dealer) {
        Map<String, Integer> matchResults = new LinkedHashMap<>();
        results.forEach((result, count) -> {
            matchResults.put(result.getValue(), count);
        });
        this.dealerMatchResults = matchResults;
        this.name = dealer.getName();
    }

    public static DealerMatchResultsDto from(BlackJackGame blackJackGame) {
        return new DealerMatchResultsDto(blackJackGame.getDealerMatchResult(), blackJackGame.getDealer());
    }

    public Map<String, Integer> getDealerMatchResults() {
        return dealerMatchResults;
    }

    public String getName() {
        return name;
    }
}
