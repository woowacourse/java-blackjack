package blackjack.domain;

import blackjack.domain.entry.Participant;

import java.util.Map;

public class BettingResult {
    private final Map<Participant, Integer> bettingResult;

    public BettingResult(Map<Participant, Integer> bettingResult) {
        this.bettingResult = bettingResult;
    }

    public Map<Participant, Integer> getBettingResult() {
        return bettingResult;
    }
}
