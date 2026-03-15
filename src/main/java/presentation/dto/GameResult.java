package presentation.dto;

import domain.member.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public record GameResult(
        Map<String, Integer> memberAmount
) {
    public static GameResult from(Map<Player, Integer> playerAmounts, int dealerAmount) {
        Map<String, Integer> results = new LinkedHashMap<>();
        results.put("딜러", dealerAmount);
        playerAmounts.forEach((member, amount) ->
                results.put(member.getName(), amount));
        return new GameResult(results);
    }
}
