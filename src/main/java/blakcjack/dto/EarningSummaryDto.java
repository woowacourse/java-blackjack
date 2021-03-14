package blakcjack.dto;

import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

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
        final List<EarningDto> playerEarnings = getPlayerEarnings(dealer, players);
        final EarningDto dealerEarning = EarningDto.of(dealer.getNameValue(), calculateDealerEarning(playerEarnings));
        return new EarningSummaryDto(dealerEarning, playerEarnings);
    }

    private static List<EarningDto> getPlayerEarnings(final Participant dealer, final List<Participant> players) {
        final Dealer castedDealer = Dealer.class.cast(dealer);
        return players.stream()
                .map(Player.class::cast)
                .map(player -> EarningDto.of(player.getNameValue(), player.calculateEarning(castedDealer)))
                .collect(Collectors.toList());
    }

    private static int calculateDealerEarning(final List<EarningDto> playerEarnings) {
        return -1 * playerEarnings.stream()
                .mapToInt(EarningDto::getEarning)
                .sum();
    }

    public EarningDto getDealerEarning() {
        return dealerEarning;
    }

    public List<EarningDto> getPlayerEarnings() {
        return playerEarnings;
    }
}
