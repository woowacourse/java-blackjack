package dto;

import domain.gamer.Player;
import java.util.Map;

public record GameResultDto(Map<Player, Integer> gameResult) {
}
