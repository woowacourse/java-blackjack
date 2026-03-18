package view.dto;

import java.util.List;

public record ParticipantsProfit(
        int dealerProfit,
        List<PlayerProfit> playersProfitResult
) {

}
