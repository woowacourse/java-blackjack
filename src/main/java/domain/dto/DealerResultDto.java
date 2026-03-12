package domain.dto;

import domain.GameResult;
import domain.participant.Dealer;

public class DealerResultDto {

    private final String name;
    private final int profit;

    private DealerResultDto(String name, int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static DealerResultDto of(Dealer dealer, GameResult gameResult) {
        return new DealerResultDto(
                dealer.getName(),
                gameResult.getDealerProfit()
        );
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
