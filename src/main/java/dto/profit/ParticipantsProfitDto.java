package dto.profit;

import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;

public record ParticipantsProfitDto(List<ParticipantProfitDto> participantsProfitDto) {

    public static ParticipantsProfitDto of(final Players players, final Dealer dealer) {
        final List<ParticipantProfitDto> result = players.calculateProfits(dealer).entrySet().stream()
                .map(ParticipantProfitDto::of)
                .toList();
        return new ParticipantsProfitDto(result);
    }
}
