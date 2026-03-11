package dto;

import domain.WinningCondition;

public record GameResult(String name, WinningCondition winningCondition) {
}
