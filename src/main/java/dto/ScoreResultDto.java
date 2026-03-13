package dto;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

// todo: 매개변수 2개로 수정
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
