package blackjack.dto;

import blackjack.domain.bet.Bet;
import blackjack.domain.bet.Profit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameResult;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record FinalProfitsDto(
    Map<String, Integer> nicknameProfitMap,
    int dealerProfit
) {

    public static FinalProfitsDto of(final Players players, final Dealer dealer) {
        final Map<String, Integer> nicknameProfitMap = new LinkedHashMap<>();
        players.all().forEach(player -> {
            final Bet bet = player.getBet()
                    .orElseThrow(() -> new IllegalStateException("Bet 객체가 아직 초기화되지 않았습니다."));
            final GameResult gameResult = GameResult.calculate(player, dealer);
            nicknameProfitMap.put(player.getNickname(), Profit.calculate(bet, gameResult));
        });
        final int dealerProfit = nicknameProfitMap.values().stream()
            .mapToInt(Integer::intValue)
            .sum() * -1;
        return new FinalProfitsDto(nicknameProfitMap, dealerProfit);
    }

}
