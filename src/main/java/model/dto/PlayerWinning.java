package model.dto;

import model.MatchStatus;

public record PlayerWinning(String name, MatchStatus matchStatus) {
}
