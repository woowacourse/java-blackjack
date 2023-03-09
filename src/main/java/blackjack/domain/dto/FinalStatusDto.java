package blackjack.domain.dto;

import java.util.Map;

public class FinalStatusDto {
    private final UserDto dealer;
    private final Integer dealerScore;
    private final Map<UserDto, Integer> userScores;

    public FinalStatusDto(UserDto dealer, int dealerScore, Map<UserDto, Integer> userScores) {
        this.dealer = dealer;
        this.dealerScore = dealerScore;
        this.userScores = userScores;
    }

    public Integer getDealerScore() {
        return dealerScore;
    }

    public UserDto getDealer() {
        return dealer;
    }

    public Map<UserDto, Integer> getUserScores() {
        return userScores;
    }
}
