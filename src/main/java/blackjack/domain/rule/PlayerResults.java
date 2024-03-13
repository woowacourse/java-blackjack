package blackjack.domain.rule;

import java.util.List;

public class PlayerResults {

    private final List<PlayerResult> results;

    public PlayerResults(List<PlayerResult> results) {
        this.results = results;
    }

    public List<PlayerResult> getResults() {
        return results;
    }
}
