package blackjack.domain.result.model;

import blackjack.domain.participant.attribute.Name;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class ProfitDto {
    private final String name;
    private final double profit;

    public ProfitDto(Name name, double profit) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        this.name = name.getName();
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public double getProfit() {
        return profit;
    }
}
