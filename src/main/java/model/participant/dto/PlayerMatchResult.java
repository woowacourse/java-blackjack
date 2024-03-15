package model.participant.dto;

import model.participant.MatchResult;
import model.participant.Name;

public record PlayerMatchResult(Name name, MatchResult matchResult) {
    public String getNameAsString() {
        return name.getValue();
    }
}
