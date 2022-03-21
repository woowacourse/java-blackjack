package blackjack.dto.score;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TotalScoreDto {
    private final List<ScoreDto> totalScore = new ArrayList<>();

    public TotalScoreDto(Dealer dealer, Players players) {
        totalScore.add(new ScoreDto(dealer));
        players.getPlayers().forEach(
                player -> totalScore.add(new ScoreDto(player))
        );
    }

    public List<ScoreDto> getTotalScore() {
        return Collections.unmodifiableList(totalScore);
    }
}
