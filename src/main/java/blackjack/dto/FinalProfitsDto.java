package blackjack.dto;

import blackjack.domain.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public record FinalProfitsDto(
    Map<String, Integer> nicknameProfitMap,
    int dealerProfit
) {

    public static FinalProfitsDto from(final Players players) {
        final Map<String, Integer> nicknameProfitMap = new LinkedHashMap<>();
        players.all().forEach(player ->
            nicknameProfitMap.put(player.getNickname(), player.getProfit()));
        final int dealerProfit = nicknameProfitMap.values().stream()
            .mapToInt(Integer::intValue)
            .sum() * -1;
        return new FinalProfitsDto(nicknameProfitMap, dealerProfit);
    }

}
