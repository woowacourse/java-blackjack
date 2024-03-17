package dto;

import domain.game.GameResult;
import domain.participant.PlayerName;

public record PlayerGameResultDto(PlayerName playerName, GameResult gameResult) {
}
