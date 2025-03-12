package dto;

import java.util.Map;
import object.game.BattleResult;

public record BattleResultResponse(String nickname, Map<BattleResult, Integer> battleResult) {
}
