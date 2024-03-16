package model.participant.dto;

import model.participant.MatchState;
import model.participant.Name;

public record PlayerMatchResult(Name name, MatchState matchState) {
    public String getNameAsString() {
        return name.getValue();
    }
}
