package blackjack.dto;

import blackjack.domain.player.Money;
import blackjack.domain.player.Player;

public class DealerProfitDto {

    private final String name;
    private final int profit;

    public DealerProfitDto(Player dealer, Money profit) {
        this.name = dealer.getName();
        this.profit = profit.getAmount();
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
