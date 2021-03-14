package blackjack.domain.result;

import blackjack.domain.participant.Participant;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {
    private final Map<Participant, BigDecimal> profitResult;

    public ProfitResult(Map<Participant, BigDecimal> profitResult) {
        this.profitResult = new LinkedHashMap<>(profitResult);
    }

    public Map<Participant, BigDecimal> getProfitResult() {
        return Collections.unmodifiableMap(profitResult);
    }
}
