package domain.dto;

import domain.constant.GameResult;
import domain.participant.PlayerName;

public record PlayerGameResultDto(PlayerName playerName, GameResult gameResult) {
}
