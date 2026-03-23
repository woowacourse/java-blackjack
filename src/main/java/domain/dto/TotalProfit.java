package domain.dto;

import domain.participant.BetMoney;
import java.util.List;

public record TotalProfit(
        BetMoney dealerProfit,
        List<Profit> playerProfits
) {
}
