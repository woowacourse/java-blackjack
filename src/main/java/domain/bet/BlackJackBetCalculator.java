package domain.bet;

import domain.GameResult;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackBetCalculator {
    private Map<String,Bet> playersBet = new HashMap<>();

    public BlackJackBetCalculator(Map<String,Integer> playersBet) {
        this.playersBet = playersBet.entrySet().stream()
                .collect(Collectors
                        .toMap(Map.Entry::getKey, entry -> new Bet(entry.getValue())));
    }

    public Bet determineBettingAmount(Dealer dealer, Player player) {
        GameResult playResult = GameResult.calculateResult(dealer,player);
        Bet playerBet = findBetByPlayer(player);

        if(playResult == GameResult.WIN){return calcWinningBet(player,playerBet);}
        if(playResult == GameResult.LOSE){return playerBet.getLosingPrize();}
        return playerBet.getDrawPrize();
    }

    private Bet calcWinningBet(Player player, Bet playerBet){
        if(player.isBlackJack()){
            return playerBet.getBlackJackPrize();
        }
        return playerBet.getWinningPrize();
    }

    private Bet findBetByPlayer(Player player){
        return playersBet.get(player.getName());
    }
}
