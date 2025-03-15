package Blackjack.dto;

import Blackjack.domain.game.GameStatus;
import java.util.Map;

public record GameResultDto(String name, Map<GameStatus, Integer> gameResults) {
}
