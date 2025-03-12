package dto;

import java.util.Map;

public record BattleResultResponse(String nickname, Map<String, Integer> battleResult) {
}
