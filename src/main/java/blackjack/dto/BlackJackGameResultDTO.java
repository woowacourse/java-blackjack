package blackjack.dto;

import blackjack.domain.BlackJackResults;
import blackjack.domain.MatchResults;
import blackjack.domain.Name;
import blackjack.domain.ResultType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameResultDTO {
    private final Map<String, List<String>> gameResults;

    private BlackJackGameResultDTO(final Map<String, List<String>> gameResults) {
        this.gameResults = gameResults;
    }

    public static BlackJackGameResultDTO of(final BlackJackResults blackJackResults) {
        Map<String, List<String>> results = new LinkedHashMap<>();
        Map<Name, MatchResults> participantResults = blackJackResults.getParticipants();
        participantResults.keySet()
                .forEach(name ->
                        results.put(name.getValue(),
                                participantResults.get(name).getResults().stream()
                                        .map(ResultType::getType)
                                        .collect(Collectors.toList())));
        return new BlackJackGameResultDTO(results);
    }

    public Map<String, List<String>> getGameResults() {
        return gameResults;
    }
}
