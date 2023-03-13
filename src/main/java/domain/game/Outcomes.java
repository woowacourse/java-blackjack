package domain.game;

import domain.player.Name;
import java.util.HashMap;
import java.util.Map;

public class Outcomes {
    Map<Name, Outcome> outcomes;

    public Outcomes() {
        outcomes = new HashMap<>();
    }

    public void addOutcome(final Name name, final Outcome outcome) {
        outcomes.put(name, outcome);
    }

    public Outcome getOutcome(final Name name){
        return outcomes.get(name);
    }
}
