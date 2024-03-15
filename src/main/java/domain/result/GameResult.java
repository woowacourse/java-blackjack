package domain.result;

import domain.participant.Participant;
import vo.Profit;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Participant, Profit> result;

    public GameResult() {
        this(new LinkedHashMap<>());
    }

    public GameResult(final Map<Participant, Profit> result) {
        this.result = new LinkedHashMap<>(result);
    }

    public void put(final Participant participant, final Profit profit) {
        result.put(participant, profit);
    }

    public Map<Participant, Profit> getResult() {
        return Map.copyOf(result);
    }
}
