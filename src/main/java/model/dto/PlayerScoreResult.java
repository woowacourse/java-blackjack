package model.dto;

import model.participant.Name;
import view.Victory;

public record PlayerScoreResult(Name name, Victory victory) {
    public String getNameAsString(){
        return name.getValue();
    }

    public String getVictoryAsString(){
        return victory.getValue();
    }
}
