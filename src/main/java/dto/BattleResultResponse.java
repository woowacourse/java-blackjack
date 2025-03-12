package dto;

import java.util.Map;
import object.game.GameResult;

public record BattleResultResponse(String nickname, Map<GameResult, Integer> battleResult) {
}
