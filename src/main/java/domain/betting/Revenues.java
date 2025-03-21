package domain.betting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Revenues {
    private final List<Revenue> revenues;

    public Revenues(List<Revenue> revenues) {
        this.revenues = new ArrayList<>(revenues);
    }

    public Revenue calculateDealerRevenue() {
        int totalPlayersRevenue = revenues.stream()
                .mapToInt(revenue -> revenue.money())
                .sum();
        return new Revenue("dealer", -totalPlayersRevenue);
    }

    public List<Revenue> getRevenues() {
        return Collections.unmodifiableList(revenues);
    }
}
