package blackjack.dto;

import blackjack.domain.judgement.BettingMoneyInfo;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Map;
import java.util.stream.Collectors;

public record FinalProfitDto(
        Map<Name, Double> bettingMoneyInfo,
        double profitByDealer
) {

    public static FinalProfitDto of(Participants participants, BettingMoneyInfo bettingMoneyInfo) {
        Map<Name, Double> profitByPlayer = calculatePlayerProfit(participants, bettingMoneyInfo);

        double profitByDealer = calculateDealerProfit(profitByPlayer);

        return new FinalProfitDto(profitByPlayer, profitByDealer);
    }

    private static Map<Name, Double> calculatePlayerProfit(Participants participants, BettingMoneyInfo bettingMoneyInfo) {
        Players players = participants.players();
        Dealer dealer = participants.dealer();
        return players.all().stream()
                .collect(Collectors.toUnmodifiableMap(
                                Player::getName,
                                player -> player.calculateGameResult(dealer)
                                        .calculateProfit(bettingMoneyInfo.findMoneyByName(player.getName()))
                        )
                );
    }

    private static double calculateDealerProfit(Map<Name, Double> profitByPlayer) {
        return profitByPlayer.values().stream()
                .mapToDouble(money -> -money)
                .sum();
    }


}
