package blakcjack.dto;

import java.util.List;

public class EarningSummaryDto {
    private final EarningDto dealerEarning;
    private final List<EarningDto> playerEarnings;

    private EarningSummaryDto(final EarningDto dealerEarning, final List<EarningDto> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = playerEarnings;
    }

    // TODO : 구조가 확정되면 나중에 수정
//    public static EarningSummaryDto of(final Participant dealer, final List<Participant> players) {
//        final List<EarningDto> playerEarnings = players.stream()
//                .map(EarningDto::of)
//                .collect(Collectors.toList());
//
//        return new EarningSummaryDto(EarningDto.of(dealer), playerEarnings);
//    }

    public EarningDto getDealerEarning() {
        return dealerEarning;
    }

    public List<EarningDto> getPlayerEarnings() {
        return playerEarnings;
    }
}
