package dto;

import model.MatchStatus;

public record PlayerWinning(PlayerName name, MatchStatus matchStatus) {
}
