package blakcjack.dto;

import blakcjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class EarningSummaryDto {
    private final EarningDto dealerEarning;
    private final List<EarningDto> playerEarnings;

    private EarningSummaryDto(final EarningDto dealerEarning, final List<EarningDto> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = playerEarnings;
    }

    public static EarningSummaryDto of(final Participant dealer, final List<Participant> players) {
        final List<EarningDto> playerEarnings = players.stream()
                .map(EarningDto::of)
                .collect(Collectors.toList());

        return new EarningSummaryDto(EarningDto.of(dealer), playerEarnings);
    }

    public EarningDto getDealerEarning() {
        return dealerEarning;
    }

    public List<EarningDto> getPlayerEarnings() {
        return playerEarnings;
    }
}
