package dto;

import domain.participant.BattleResult;
import java.util.Map;

public record BattleResultResponse(String nickname, Map<BattleResult, Integer> battleResult) {
}
