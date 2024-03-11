package model.dto;

import java.util.EnumMap;

public record DealerScoreResult(EnumMap<MatchResult, Integer> scoreStorage) {
    public String getVictoryScoreAsString(MatchResult matchResult){
        return scoreStorage.get(matchResult) + matchResult.getValue();
    }
}
