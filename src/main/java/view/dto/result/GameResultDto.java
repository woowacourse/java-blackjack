package view.dto.result;

import domain.participant.Player;
import domain.result.GameResult;
import domain.result.GameResultStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public record GameResultDto(Map<String, String> gameResult, Map<String, Integer> dealerResult) {

    public static GameResultDto from(final GameResult gameResult, final GameResult dealerResult) {
        return new GameResultDto(convertToPlayerResult(gameResult), convertToDealerResult(dealerResult));
    }

    private static Map<String, String> convertToPlayerResult(final GameResult gameResult) {
        Map<String, String> resultDto = new LinkedHashMap<>();
        gameResult.getResult()
                  .forEach((key, value) -> {
                      resultDto.put(key.name().value(), value.getValue());
                  });
        return resultDto;
    }

    private static Map<String, Integer> convertToDealerResult(final GameResult dealerResult) {
        Map<Player, GameResultStatus> result = dealerResult.getResult();
        Map<String, Integer> resultDto = new LinkedHashMap<>();
        for (GameResultStatus gameResultStatus : GameResultStatus.values()) {
            int count = (int) result.values()
                                    .stream()
                                    .filter(status -> status == gameResultStatus)
                                    .count();
            resultDto.put(gameResultStatus.getValue(), count);
        }
        resultDto.entrySet()
                 .removeIf(entry -> entry.getValue() == 0);
        return resultDto;
    }
}
