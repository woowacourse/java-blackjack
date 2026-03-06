package blackjack;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class BlackJackJudge {


    public HashMap<Player, GameResult> judge(Players players, Dealer dealer) {
        HashMap<Player, GameResult> result = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            if(player.isBust()){
                result.put(player, GameResult.LOSE);
                continue;
            }

            if (dealer.isBust()) {
                result.put(player, GameResult.WIN);
                continue;
            }

            int playerTotalPoint = player.getTotalPoint();
            int dealerTotalPoint=dealer.getTotalPoint();
            if(playerTotalPoint >dealerTotalPoint){
                result.put(player, GameResult.WIN);
                continue;
            }

            if(playerTotalPoint <dealerTotalPoint){
                result.put(player, GameResult.LOSE);
                continue;
            }
            result.put(player, GameResult.TIE);
        }


        return result;
    }
}
