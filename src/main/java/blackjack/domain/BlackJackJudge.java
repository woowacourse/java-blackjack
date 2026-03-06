package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BlackJackJudge {


    public HashMap<Player, GameResult> judge(Players players, Dealer dealer) {
        HashMap<Player, GameResult> result = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            result.put(player, judgeGameResult(player, dealer));
        }

        return result;
    }
    
    private GameResult judgeGameResult(Player player, Dealer dealer) {
        if(player.isBust()){
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        return judgeNearestBlackJackPoint(player, dealer);
    }

    private static GameResult judgeNearestBlackJackPoint(Player player, Dealer dealer) {
        int playerTotalPoint = player.getTotalPoint();
        int dealerTotalPoint= dealer.getTotalPoint();

        if(playerTotalPoint >dealerTotalPoint){
            return GameResult.WIN;
        }

        if(playerTotalPoint <dealerTotalPoint){
            return GameResult.LOSE;
        }
        return GameResult.TIE;
    }
}
