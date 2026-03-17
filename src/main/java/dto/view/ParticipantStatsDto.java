package dto.view;

import java.util.List;

public record ParticipantStatsDto(
        DealerStatDto dealerStat,
        List<PlayerOutcomeDto> playerStats
) {
}
