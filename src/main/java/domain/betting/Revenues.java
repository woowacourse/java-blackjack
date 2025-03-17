package domain.betting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Revenues {
    private final List<Revenue> revenues;

    public Revenues(List<Revenue> revenues) {
        this.revenues = new ArrayList<>(revenues);
    }

    public Revenue findByName(String name) {
        return revenues.stream()
                .filter(revenue -> revenue.playerName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어의 수익 정보가 없습니다."));
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
