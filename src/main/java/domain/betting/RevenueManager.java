package domain.betting;

import domain.participant.Name;
import java.util.HashMap;
import java.util.Map;

public class RevenueManager {
    private final Map<Name, Revenue> finalRevenue;

    public RevenueManager(Map<Name, Revenue> finalRevenue) {
        this.finalRevenue = new HashMap<>(finalRevenue);
    }
}
