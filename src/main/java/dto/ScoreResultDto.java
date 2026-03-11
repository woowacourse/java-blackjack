package dto;

import domain.Dealer;
import domain.Player;
import java.util.List;

public record ScoreResultDto(HandDto dealerHandDto, int dealerScore, List<PlayerHandScoreDto> playerHandScoreDtos) {

    public static ScoreResultDto of(Dealer dealer, List<Player> players) {
        HandDto dealerHand = HandDto.from(dealer.getHand());
        int dealerScore = dealer.getScore();
        List<PlayerHandScoreDto> playerHandScoreDtos = players.stream()
                .map(PlayerHandScoreDto::from)
                .toList();
        return new ScoreResultDto(dealerHand, dealerScore, playerHandScoreDtos);
    }
}
