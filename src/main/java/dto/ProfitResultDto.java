package dto;

import domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfitResultDto {

    private final int dealerProfitResult;
    private final Map<String, Integer> playersProfitResult;

    public ProfitResultDto(int dealerProfitResult, Map<Player, Integer> playersProfitResult) {
        this.dealerProfitResult = dealerProfitResult;

        this.playersProfitResult = playersProfitResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public int getDealerProfitResult() {
        return dealerProfitResult;
    }

    public Map<String, Integer> getPlayersProfitResult() {
        return playersProfitResult;
    }
}
