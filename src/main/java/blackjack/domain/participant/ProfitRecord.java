package blackjack.domain.participant;

import java.util.Map;

public class ProfitRecord {
    private final Map<Name, ProfitAmount> profitRecord;

    public ProfitRecord(Map<Name, ProfitAmount> profitRecord) {
        this.profitRecord = profitRecord;
    }

    public ProfitAmount calculateDealerProfit() {
        return profitRecord.values().stream()
                .reduce(new ProfitAmount(0), ProfitAmount::add)
                .inverse();
    }

    public Map<Name, ProfitAmount> getProfitRecord() {
        return profitRecord;
    }
}
