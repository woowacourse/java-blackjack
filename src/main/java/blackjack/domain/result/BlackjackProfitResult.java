package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackProfitResult {

    private final Dealer dealer;
    private final Map<Player, BettingMoney> playersInfo;

    public BlackjackProfitResult(Dealer dealer, Map<Player, BettingMoney> playersInfo) {
        this.dealer = dealer;
        this.playersInfo = playersInfo;
    }

    public Map<Participant, BlackjackProfit> calculateParticipantsProfit() {
        final Map<Participant, BlackjackProfit> profitResult = new LinkedHashMap<>();
        profitResult.put(dealer, calculateDealerProfit());
        profitResult.putAll(calculatePlayersProfit());
        return profitResult;
    }

    private BlackjackProfit calculateDealerProfit() {
        final Map<Player, BlackjackProfit> playersResult = calculatePlayersProfit();
        double dealerProfit = 0;
        for (BlackjackProfit blackjackProfit : playersResult.values()) {
            dealerProfit -= blackjackProfit.getProfit();
        }
        return BlackjackProfit.from(dealerProfit);
    }

    private Map<Player, BlackjackProfit> calculatePlayersProfit() {
        final Map<Player, BlackjackProfit> playersProfitResult = new LinkedHashMap<>();
        for (Map.Entry<Player, BettingMoney> playerInfo : playersInfo.entrySet()) {
            final Player player = playerInfo.getKey();
            final BlackjackMatch match = BlackjackMatch.of(player, dealer);
            final BlackjackProfit profit = BlackjackProfit.of(match, playerInfo.getValue());
            playersProfitResult.put(player, profit);
        }
        return playersProfitResult;
    }
}
