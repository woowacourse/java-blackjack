package domain;

import domain.constant.GameResult;
import domain.participant.Player;

import java.util.Map;

public record GameResults(Map<Player, GameResult> playerGameResults) {
}
