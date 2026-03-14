package domain.betting;

import domain.participant.Name;
import java.util.HashMap;
import java.util.Map;

public class Revenues {
    private final Map<Name, Revenue> finalRevenue;

    public Revenues(Map<Name, Revenue> finalRevenue) {
        this.finalRevenue = new HashMap<>(finalRevenue);
    }
}
