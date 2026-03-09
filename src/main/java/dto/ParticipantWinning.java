package dto;

import constant.MatchStatus;
import java.util.List;
import java.util.Map;

public record ParticipantWinning(Map<MatchStatus, Integer> dealerWinning, List<PlayerWinning> playersWinning) {
}
