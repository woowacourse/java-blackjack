package dto;

import java.util.EnumMap;
import java.util.Map;
import vo.GameResult;

public class ParticipantsScoreDTO {
    private final EnumMap<GameResult, Integer> dealerScore;
    private final Map<String, GameResult> userScore;

    public ParticipantsScoreDTO(EnumMap<GameResult, Integer> dealerScore, Map<String, GameResult> userScore) {
        this.dealerScore = dealerScore;
        this.userScore = userScore;
    }

    public EnumMap<GameResult, Integer> getDealerScore() {
        return dealerScore;
    }

    public Map<String, GameResult> getUserScore() {
        return userScore;
    }
}
