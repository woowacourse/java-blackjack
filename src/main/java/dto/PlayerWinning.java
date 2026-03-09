package dto;

import constant.MatchStatus;

public record PlayerWinning(PlayerName name, MatchStatus matchStatus) {
}
