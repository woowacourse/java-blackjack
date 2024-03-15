package model.participant.dto;

import java.util.EnumMap;
import model.participant.MatchResult;

public record DealerMatchResult(EnumMap<MatchResult, Integer> scoreStorage) {
    public String getVictoryScoreAsString(MatchResult matchResult){
        return scoreStorage.get(matchResult) + matchResult.getValue();
    }
}
