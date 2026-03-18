package domain.betting;

import java.util.List;
import view.dto.PlayerProfitResult;

public record ParticipantsProfitResult(
        int dealerProfit,
        List<PlayerProfitResult> playersProfitResult
) {

}
