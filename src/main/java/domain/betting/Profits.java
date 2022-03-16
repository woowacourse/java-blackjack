package domain.betting;

import domain.participant.Name;
import java.util.Map;

public class Profits {

    private final Map<Name, Double> profits;

    public Profits(Map<Name, Double> profits) {
        this.profits = Map.copyOf(profits);
    }

    public double getProfit(Name name) {
        return profits.get(name);
    }

    public double calculateDealerProfit() {
        return profits.keySet().stream()
                .mapToDouble(profits::get)
                .sum() * (-1);
    }
}
