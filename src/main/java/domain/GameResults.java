package domain;

import domain.constant.GameResult;
import domain.participant.Player;

import java.util.List;
import java.util.Map;

public record GameResults(List<GameResult> dealerGameResult, Map<Player, GameResult> playerGameResults) {
}
