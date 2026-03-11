package service;

import domain.Dealer;
import domain.Player;
import dto.ScoreResultDto;
import java.util.List;

public class BlackJackResultService {

    public ScoreResultDto createScoreResultDto(Dealer dealer, List<Player> players) {
        return ScoreResultDto.of(dealer, players);
    }
}
