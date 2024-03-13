package service.dto;

import java.util.EnumMap;
import model.casino.MatchResult;

public record DealerScoreResult(EnumMap<MatchResult, Integer> scoreStorage) {
    public String getVictoryScoreAsString(MatchResult matchResult){
        return scoreStorage.get(matchResult) + matchResult.getValue();
    }
}
