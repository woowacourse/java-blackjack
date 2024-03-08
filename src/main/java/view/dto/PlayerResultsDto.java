package view.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.GameResultStatus;
import domain.PlayersGameResult;

public class PlayerResultsDto {

    private final Map<String, GameResultStatus> result;

    public PlayerResultsDto(final PlayersGameResult playersGameResult) {
        this.result = new LinkedHashMap<>();
        playersGameResult.getResult()
                .forEach((player, resultStatus) -> result.put(player.name()
                        .value(), resultStatus));
    }

    public String parseResult(){
        return result.keySet()
                .stream()
                .map(player -> player +  ": " + result.get(player).getValue() + System.lineSeparator())
                .reduce((a, b) -> a + b)
                .orElse("");
    }
}
