package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participants;

import java.util.HashMap;
import java.util.Map;

public record FinalProfitDto(
        Map<String, Integer> profitByPlayer,
        int profitByDealer
) {

    public static FinalProfitDto of(Participants participants, Map<String, Integer> bettingMoneyByPlayer) {
        Map<String, Integer> profitByPlayer = new HashMap<>();
        Dealer dealer = participants.dealer();
        participants.players().all().forEach(player -> {
                    GameResult gameResult = player.calculateGameResult(dealer);
                    if (gameResult == GameResult.WIN) {
                        profitByPlayer.put(player.getNickname(), bettingMoneyByPlayer.get(player.getNickname()));
                    }
                    if (gameResult == GameResult.DRAW) {
                        profitByPlayer.put(player.getNickname(), 0);
                    }
                    if (gameResult == GameResult.LOSE) {
                        profitByPlayer.put(player.getNickname(), -bettingMoneyByPlayer.get(player.getNickname()));
                    }
                }
        );

        int profitByDealer = profitByPlayer.values().stream()
                .mapToInt(money -> -money)
                .sum();

        return new FinalProfitDto(profitByPlayer, profitByDealer);
    }

}
