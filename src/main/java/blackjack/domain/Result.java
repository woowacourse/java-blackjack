package blackjack.domain;

import java.util.Map;

public class Result {

    private final Map<Player, ResultStatus> results;

    public Result(Map<Player, ResultStatus> results) {
        this.results = results;
    }

    public Map<Player, ResultStatus> getResults() {
        return results;
    }
}
