package domain.game;

import domain.player.Name;

import java.util.Map;

public class Results {
    private final Map<Name, Result> results;

    public Results(final Map<Name, Result> results) {
        this.results = results;
    }

    public Map<Name, Result> getResults() {
        return results;
    }
}
