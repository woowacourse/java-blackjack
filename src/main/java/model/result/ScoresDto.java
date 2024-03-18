package model.result;

import java.util.List;

public record ScoresDto(ScoreDto dealerScore, List<ScoreDto> playerScores) {

}
