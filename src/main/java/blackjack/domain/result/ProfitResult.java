package blackjack.domain.result;

import blackjack.domain.money.Profits;
import blackjack.domain.participant.Participant;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {
    private final Map<Participant, Profits> profitResult;

    public ProfitResult(Map<Participant, Profits> profitResult) {
        this.profitResult = new LinkedHashMap<>(profitResult);
    }

    public Map<Participant, Profits> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}
