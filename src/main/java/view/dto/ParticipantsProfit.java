package view.dto;

import domain.betting.Settlement;
import domain.participant.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ParticipantsProfit(
        int dealerProfit,
        List<PlayerProfit> playersProfitResult
) {
    public static ParticipantsProfit from(Settlement settlement) {
        List<PlayerProfit> playerProfits = new ArrayList<>();
        for (Map.Entry<Name, Integer> entry : settlement.getPlayerProfits().entrySet()) {
            playerProfits.add(new PlayerProfit(entry.getKey().value(), entry.getValue()));
        }
        return new ParticipantsProfit(settlement.getDealerProfit(), playerProfits);
    }
}