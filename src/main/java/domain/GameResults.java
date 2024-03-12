package domain;

import domain.constant.GameResult;
import domain.participant.PlayerName;

import java.util.Map;

public record GameResults(Map<PlayerName, GameResult> playerGameResults) {
}
