package blackjack.dto;

import blackjack.domain.game.Money;
import blackjack.domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public record PlayerGameResultsDto(Map<String, Integer> playerIncomeResults) {

    public static PlayerGameResultsDto fromPlayerBetResults(Map<Player, Money> playerBetResults) {
        Map<String, Integer> playerIncomeResults = new LinkedHashMap<>();

        for (Player player : playerBetResults.keySet()) {
            playerIncomeResults.put(player.getName().value(), playerBetResults.get(player).value());
        }

        return new PlayerGameResultsDto(playerIncomeResults);
    }
}
