package dto.view;

import java.util.List;

public record ParticipantProfitDto(
        int dealerProfit,
        List<PlayerProfitDto> playerProfits
) {
}
