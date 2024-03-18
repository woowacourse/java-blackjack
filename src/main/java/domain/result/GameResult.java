package domain.result;

import domain.participant.Participant;
import domain.vo.Profit;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Participant, Profit> result;

    public GameResult() {
        this.result = new LinkedHashMap<>();
    }

    public void put(final Participant participant, final Profit profit) {
        result.put(participant, profit);
    }

    public Map<Participant, Profit> getResult() {
        return new LinkedHashMap<>(result);
    }
}
