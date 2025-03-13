package domain.bet;

import domain.GameResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackBetCalculator {
    private Map<String,Bet> playersBet = new HashMap<>();

    public BlackJackBetCalculator(Map<String,Integer> playersBet) {
        this.playersBet = playersBet.entrySet().stream()
                .collect(Collectors
                        .toMap(Map.Entry::getKey, entry -> new Bet(entry.getValue())));
    }

    public int determineBettingAmount(Dealer dealer, Player player) {
        GameResult playResult = GameResult.calculateResult(dealer,player);
        Bet playerBet = findBetByPlayer(player);

        if(playResult == GameResult.WIN){return calcWinningBet(player,playerBet);}
        if(playResult == GameResult.LOSE){return playerBet.getLosingPrize();}
        return playerBet.getDrawPrize();
    }

    public int getDealerBetResult(Dealer dealer, List<Player> players){
        return -getPlayerBetResult(dealer,players).values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String,Integer> getPlayerBetResult(Dealer dealer, List<Player> players){
        return players.stream().collect(Collectors.toMap(
                Player::getName,
                player -> determineBettingAmount(dealer,player)));
    }

    private int calcWinningBet(Player player, Bet playerBet){
        if(player.isBlackJack()){
            return playerBet.getBlackJackPrize();
        }
        return playerBet.getWinningPrize();
    }

    private Bet findBetByPlayer(Player player){
        return playersBet.get(player.getName());
    }
}
