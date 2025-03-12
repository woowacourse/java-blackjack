package model;

import model.participant.Player;
import model.score.MatchResult;

import java.util.EnumMap;
import java.util.HashMap;

public record VictoryResultDto(HashMap<Player, MatchResult> playersMatchResult,
                               EnumMap<MatchResult, Integer> dealerMatchResult) {
}
