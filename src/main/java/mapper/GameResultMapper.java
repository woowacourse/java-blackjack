package mapper;

import domain.result.GameResult;
import view.dto.result.GameResultDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultMapper {
    private GameResultMapper() {
    }

    public static GameResultDto gameResultToGameResultDto(final GameResult gameResult) {
        return new GameResultDto(convertToParticipantResult(gameResult));
    }

    private static Map<String, Integer> convertToParticipantResult(final GameResult gameResult) {
        Map<String, Integer> resultDto = new LinkedHashMap<>();
        gameResult.getResult()
                  .forEach((playerName, profit) -> resultDto.put(playerName.name(), (int) profit.value()));
        return resultDto;
    }
}
