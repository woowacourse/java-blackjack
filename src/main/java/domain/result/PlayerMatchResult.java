package domain.result;

import domain.pariticipant.Player;

import java.util.Map;

public record PlayerMatchResult(Map<Player, MatchCase> playerMatchResult) {
}
