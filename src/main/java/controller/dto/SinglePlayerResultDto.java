package controller.dto;

import player.Name;

public record SinglePlayerResultDto(Name name, boolean isWinner) {
}
