package service;

import domain.Dealer;
import domain.Player;
import domain.WinningStatus;
import dto.FinalResultDto;
import dto.ScoreResultDto;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackResultService {

    public ScoreResultDto createScoreResultDto(Dealer dealer, List<Player> players) {
        return new ScoreResultDto(dealer, players);
    }

    public FinalResultDto createFinalResultDto(Dealer dealer, List<Player> players) {
        Map<String, WinningStatus> playerResults = createPlayerResults(dealer, players);
        return FinalResultDto.from(playerResults);
    }

    // todo : stream 리팩토링
    private Map<String, WinningStatus> createPlayerResults(Dealer dealer, List<Player> players) {
        Map<String, WinningStatus> map = new LinkedHashMap<>();
        for(Player player : players){
            if(player.isBurst()){
                map.put(player.getName(), WinningStatus.LOSE);
                continue;
            }

            if(dealer.getHand().getSum() > player.getHand().getSum()){
                map.put(player.getName(), WinningStatus.LOSE);
            }
            if(dealer.getHand().getSum() == player.getHand().getSum()){
                map.put(player.getName(), WinningStatus.DRAW);
            }
            if(dealer.getHand().getSum() < player.getHand().getSum()){
                map.put(player.getName(), WinningStatus.WIN);
            }
        }
        return map;
    }

}

