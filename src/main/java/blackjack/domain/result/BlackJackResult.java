package blackjack.domain.result;

import blackjack.domain.participant.Player;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayersResultDto;

import java.util.EnumMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<Player, MatchResult> result;

    public BlackJackResult(Map<Player, MatchResult> result) {
        this.result = result;
    }

    public Map<MatchResult, Integer> getDealerResult() {
        Map<MatchResult, Integer> dealerResult = new EnumMap<>(MatchResult.class);
        for (MatchResult matchResult : MatchResult.values()) {
            dealerResult.put(matchResult, dealerResultCount(matchResult));
        }
        return dealerResult;
    }

    private int dealerResultCount(MatchResult matchResult) {
        return (int) result.values().stream()
                .filter(result -> result.equals(MatchResult.getDealerMatchResultByPlayer(matchResult)))
                .count();
    }

    public DealerResultDto toDealerResultDto() {
        return new DealerResultDto(getDealerResult());
    }

    public PlayersResultDto toPlayersResultDto() {
        return new PlayersResultDto(result);
    }
}
