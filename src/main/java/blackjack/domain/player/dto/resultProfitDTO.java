package blackjack.domain.player.dto;

import blackjack.domain.player.Name;
import java.util.Map;

public class resultProfitDTO {
    private final Map<Name, Integer> userProfits;
    private final int dealerProfit;

    public resultProfitDTO(Map<Name, Integer> userProfits, int dealerProfit) {
        this.userProfits = userProfits;
        this.dealerProfit = dealerProfit;
    }

    public Map<Name, Integer> getUserProfits() {
        return userProfits;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
