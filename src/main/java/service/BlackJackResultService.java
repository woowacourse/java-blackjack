package service;

import domain.Dealer;
import domain.Player;
import domain.WinningStatus;
import dto.FinalResultDto;
import dto.ScoreResultDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultService {

    public ScoreResultDto createScoreResultDto(Dealer dealer, List<Player> players) {
        return new ScoreResultDto(dealer, players);
    }

    public FinalResultDto createFinalResultDto(Dealer dealer, List<Player> players) {
        Map<String, WinningStatus> playerResults = createPlayerResults(dealer, players);
        return FinalResultDto.from(playerResults);
    }

    private Map<String, WinningStatus> createPlayerResults(Dealer dealer, List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> calculateWinningStatus(dealer, player),
                        (x, y) -> y,
                        LinkedHashMap::new)
                );
    }

    private WinningStatus calculateWinningStatus(Dealer dealer, Player player) {
        if (player.isBust()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinningStatus.WIN;
        }
        if (dealer.getHand().getSum() > player.getHand().getSum()) {
            return WinningStatus.LOSE;
        }
        if (dealer.getHand().getSum() == player.getHand().getSum()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.WIN;
    }
}
