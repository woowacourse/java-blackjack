package blackjack.domain.judgement;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Nickname;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfitCalculator {

    public static Map<Nickname, Profit> calculatePlayerProfit(Participants participants,
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

    public static Profit calculateDealerProfit(Map<Nickname, Profit> profitByPlayer) {
        Profit totalPlayerProfit = profitByPlayer.values().stream()
                .reduce(Profit.ZERO, Profit::add);

        return totalPlayerProfit.negate();
    }
}
