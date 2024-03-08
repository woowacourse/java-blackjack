package view.dto.participant;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.GameResultStatus;
import domain.participant.PlayerResults;

public class PlayerResultsDto {

    private final Map<String, GameResultStatus> result;

    public PlayerResultsDto(final PlayerResults playerResults) {
        this.result = new LinkedHashMap<>();
        playerResults.getResult()
                .forEach((player, resultStatus) -> result.put(player.name()
                        .value(), resultStatus));
    }

    public String parseResult() {
        return result.keySet()
                .stream()
                .map(player -> player + ": " + result.get(player)
                        .getValue() + System.lineSeparator())
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}
