package service;

import domain.Dealer;
import domain.Player;
import domain.WinningStatus;
import dto.FinalResultDto;
import dto.ScoreResultDto;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class BlackJackResultService {

    public ScoreResultDto createScoreResultDto(Dealer dealer, List<Player> players) {
        return new ScoreResultDto(dealer, players);
    }

    public FinalResultDto createFinalResultDto(Dealer dealer, List<Player> players) {
        SortedMap<String, WinningStatus> playerResults = createPlayerResults(dealer, players);
        return FinalResultDto.from(playerResults);
    }

    // todo : stream 리팩토링
    private SortedMap<String, WinningStatus> createPlayerResults(Dealer dealer, List<Player> players) {
        SortedMap<String, WinningStatus> map = new TreeMap<>();
        for(Player player : players){
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

