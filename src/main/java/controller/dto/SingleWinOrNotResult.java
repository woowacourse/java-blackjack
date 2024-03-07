package controller.dto;

import player.Name;

public record SingleWinOrNotResult(Name name, boolean isWinner) {
}
