package domain.dto;

import domain.GameStatus;
import java.util.Map;

public record GameResultDto(String name, Map<GameStatus, Integer> gameResults) {
}
