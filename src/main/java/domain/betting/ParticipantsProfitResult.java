package domain.betting;

import java.util.List;

public record ParticipantsProfitResult(
        int dealerProfit,
        List<PlayerProfitResult> playersProfitResult
) {

}
