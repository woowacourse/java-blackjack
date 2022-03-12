package blackjack.dto;

import blackjack.domain.game.ResultStatistics;
import java.util.Collections;
import java.util.List;

public class GameResultDto {

    private final List<ResultStatistics> results;

    public GameResultDto(List<ResultStatistics> results) {
        this.results = results;
    }

    public List<ResultStatistics> getResults() {
        return Collections.unmodifiableList(results);
    }
}
