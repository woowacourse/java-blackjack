package model.dto;

import model.MatchStatus;
import model.PlayerName;

public record PlayerWinning(PlayerName name, MatchStatus matchStatus) {
}
