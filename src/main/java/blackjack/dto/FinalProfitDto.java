package blackjack.dto;

import blackjack.domain.judgement.BettingMoneyInfo;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Nickname;
import blackjack.domain.participant.Participants;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public record FinalProfitDto(
        Map<Nickname, BigDecimal> bettingMoneyInfo,
        BigDecimal profitByDealer
) {

    public static FinalProfitDto of(Participants participants, BettingMoneyInfo bettingMoneyInfo) {
        Map<Nickname, BigDecimal> profitByPlayer = calculatePlayerProfit(participants, bettingMoneyInfo);

        BigDecimal profitByDealer = calculateDealerProfit(profitByPlayer);

        return new FinalProfitDto(profitByPlayer, profitByDealer);
    }

    private static Map<Nickname, BigDecimal> calculatePlayerProfit(Participants participants,
                                                               BettingMoneyInfo bettingMoneyInfo) {
        Players players = participants.players();
        Dealer dealer = participants.dealer();
        return players.all().stream()
                .collect(Collectors.toUnmodifiableMap(
                                Player::getNickname,
                                player -> player.calculateGameResult(dealer)
                                        .calculateProfit(bettingMoneyInfo.findMoneyByName(player.getNickname()))
                        )
                );
    }

    private static BigDecimal calculateDealerProfit(Map<Nickname, BigDecimal> profitByPlayer) {
        BigDecimal totalPlayerProfit = profitByPlayer.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPlayerProfit.negate();
    }


}
