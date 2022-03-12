package blackjack.dto;

import blackjack.domain.game.ResultStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class GameResultDto {

    private final List<ResultStatsDto> results;

    public GameResultDto(List<ResultStatistics> results) {
        this.results = results.stream()
                .map(ResultStatsDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<ResultStatsDto> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "GameResultDto{" +
                "results=" + results +
                '}';
    }
}
