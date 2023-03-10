package blackjack.dto;

import blackjack.domain.player.Player;
import blackjack.domain.result.Rank;
import blackjack.domain.result.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChallengerResultDto {

    private final Map<String, String> nameAndRanks;

    public ChallengerResultDto(Result result, List<Player> challengers) {
        Map<String, String> nameAndRanks = new LinkedHashMap<>();
        for (Player challenger : challengers) {
            Rank challengerResult = result.getChallengerResult(challenger);
            nameAndRanks.put(challenger.getName(), challengerResult.getLabel());
        }
        this.nameAndRanks = nameAndRanks;
    }

    public Map<String, String> getNameAndRanks() {
        return nameAndRanks;
    }
}
